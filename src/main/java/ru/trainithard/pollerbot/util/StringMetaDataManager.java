package ru.trainithard.pollerbot.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.exception.PollerBotException;

import java.util.AbstractMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class StringMetaDataManager {
    private static final String META_DATA_PATTERN = "(\\{.+}$)";
    private final RegexChecker regexChecker;
    private final ObjectMapper mapper;

    public String addMetaData(String source, String metaKey, Long longMetaValue) {
        return addMetaData(source, metaKey, Long.toString(longMetaValue));
    }

    public String addMetaData(String source, String metaKey, String metaValue) {
        try {
            if (hasMetaData(source)) {
                return modifyExistingMetaData(source, metaKey, metaValue);
            }
            return addNewMetaData(source, metaKey, metaValue);
        } catch (JacksonException e) {
            throw new PollerBotException("Can't serialize property", e);
        }
    }

    private boolean hasMetaData(String source) {
        return regexChecker.containsPattern(source, META_DATA_PATTERN);
    }

    private String modifyExistingMetaData(String source, String metaKey, String metaValue) throws JsonProcessingException {
        String metaData = getMetaData(source);
        Map<String, Object> metaDataMap = getMetaDataMap(metaData);
        metaDataMap.put(metaKey, metaValue);
        return source.replace(metaData, mapper.writeValueAsString(metaDataMap));
    }

    private String getMetaData(String source) {
        int startPosition = regexChecker.patternStartAt(source, META_DATA_PATTERN);
        return source.substring(startPosition);
    }

    private Map<String, Object> getMetaDataMap(String metaData) throws JsonProcessingException {
        return mapper.readValue(metaData, new TypeReference<>() {
        });
    }

    private String addNewMetaData(String source, String metaKey, String metaValue) throws JsonProcessingException {
        String newMetaData = mapper.writeValueAsString(new AbstractMap.SimpleEntry<>(metaKey, metaValue));
        return source.concat(newMetaData);
    }

    public String getMetaDataValue(String source, String metaKey) {
        try {
            Map<String, Object> metaDataMap = getMetaDataMap(getMetaData(source));
            return metaDataMap.get(metaKey).toString();
        } catch (JsonProcessingException e) {
            throw new PollerBotException("Can't deserialize property", e);
        }
    }

    public long getLongMetaData(String source, String metaKey) {
        try {
            Map<String, Object> metaDataMap = getMetaDataMap(getMetaData(source));
            return Long.parseLong(metaDataMap.get(metaKey).toString());
        } catch (JsonProcessingException e) {
            throw new PollerBotException("Can't deserialize property", e);
        } catch (NumberFormatException e) {
            throw new PollerBotException("Can't deserialize property (not a number)", e);
        }
    }

    public String stripMetaData(String source) {
        int metaDataStartIndex = source.indexOf(getMetaData(source));
        return source.substring(0, metaDataStartIndex);
    }
}
