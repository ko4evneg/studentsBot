package ru.trainithard.pollerbot.util;

import org.junit.jupiter.api.Test;
import ru.trainithard.pollerbot.service.dto.MessageKeyboard;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.trainithard.pollerbot.util.MessageConstructor.*;

class ButtonConstructorTest {
    private final ButtonConstructor buttonConstructor = new ButtonConstructor();

    @Test
    void testButtonsCreated() {
        String[] stringButtons = {"1", "2", "3", "4",
                "5", "6",
                "7", "8", "9", "10", "11", "12",
                "13", "14", "15", "16"};
        List<List<Button>> expectedButtons = List.of(
                List.of(new Button("1", "2"), new Button("3", "4")),
                List.of(new Button("5", "6")),
                List.of(new Button("7", "8"), new Button("9", "10"), new Button("11", "12")),
                List.of(new Button("13", "14"), new Button("15", "16")));

        MessageKeyboard keyboard = new MessageKeyboard(new String[]{}, new int[]{2, 1, 3, 2}, stringButtons);
        List<List<Button>> buttons = buttonConstructor.constructButtons(keyboard);

        assertEquals(expectedButtons.size(), buttons.size());
        assertThat(buttons).usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedButtons);
    }
}