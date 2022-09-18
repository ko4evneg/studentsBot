package ru.trainithard.pollerbot.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexChecker {
    public boolean containsPattern(String source, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(source);
        return matcher.find();
    }

    public int patternStartAt(String source, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(source);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }
}
