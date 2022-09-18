package ru.trainithard.pollerbot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexCheckerTest {
    private final RegexChecker regexChecker = new RegexChecker();

    @Test
    void testMatchFound() {
        assertTrue(regexChecker.containsPattern("ABC_DEF{version:35321}", "(\\{.+}$)"));
        assertTrue(regexChecker.containsPattern("COMMAND_NAME_{someword}", "(\\{.+}$)"));
    }

    @Test
    void testMatchNotFound() {
        assertFalse(regexChecker.containsPattern("ABC_DEF{version:35321", "(\\{.+}$)"));
        assertFalse(regexChecker.containsPattern("COMMAND_NAME_someword}", "(\\{.+}$)"));
    }

    @Test
    void testMatchReturnsCorrectStartIndex() {
        assertEquals(7 ,regexChecker.patternStartAt("ABC_DEF{version:35321}", "(\\{.+}$)"));
        assertEquals(13, regexChecker.patternStartAt("COMMAND_NAME_{someword}", "(\\{.+}$)"));
    }
}