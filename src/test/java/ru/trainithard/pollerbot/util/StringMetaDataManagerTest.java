package ru.trainithard.pollerbot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.trainithard.pollerbot.exception.PollerBotException;

import static org.junit.jupiter.api.Assertions.*;

class StringMetaDataManagerTest {
    private final StringMetaDataManager metaDataManager = new StringMetaDataManager(new RegexChecker(), new ObjectMapper());

    @Test
    void testModifyDataSuccess() {
        String actualData1 = metaDataManager.addMetaData("ABC_DEF{\"version\":\"35321\",\"somekey\":\"someval\"}", "version", "435");
        String actualData2 = metaDataManager.addMetaData("ABC_DEF{\"version\":\"35321\",\"somekey\":\"someval\"}", "somekey", "wowVal");

        assertEquals("ABC_DEF{\"version\":\"435\",\"somekey\":\"someval\"}", actualData1);
        assertEquals("ABC_DEF{\"version\":\"35321\",\"somekey\":\"wowVal\"}", actualData2);
    }

    @Test
    void testCreateNewDataSuccess() {
        String actualData = metaDataManager.addMetaData("ABC_DEF", "version", "777");

        assertEquals("ABC_DEF{\"version\":\"777\"}", actualData);
    }

    @Test
    void testAddNewDataSuccess() {
        String actualData = metaDataManager.addMetaData("ABC_DEF{\"somekey\":\"someval\",\"a\":\"b\"}", "version", "777");

        assertEquals("ABC_DEF{\"somekey\":\"someval\",\"a\":\"b\",\"version\":\"777\"}", actualData);
    }

    @Test
    void testGetStringMetaData() {
        String testSourceData = "ABC_DEF{\"somekey\":\"someval\",\"someIntVal\":\"235\"}";
        String metaDataValue = metaDataManager.getMetaData(testSourceData, "somekey");

        assertEquals("someval", metaDataValue);
    }

    @Test
    void testGetLongMetaData() {
        String testSourceData = "ABC_DEF{\"somekey\":\"someval\",\"someIntVal\":\"235\"}";
        long metaDataValue = metaDataManager.getLongMetaData(testSourceData, "someIntVal");

        assertEquals(235L, metaDataValue);
    }

    @Test
    void testNotALongMetaDataFails() {
        String testSourceData = "ABC_DEF{\"somekey\":\"someval\",\"someIntVal\":\"d235\"}";

        assertThrows(PollerBotException.class, () -> metaDataManager.getLongMetaData(testSourceData, "someIntVal"));
    }
}