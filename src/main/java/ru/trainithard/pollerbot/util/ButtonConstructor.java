package ru.trainithard.pollerbot.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ButtonConstructor {
    public List<List<MessageConstructor.Button>> constructButtons(int[] buttonsMarkup, String...buttons) {
        List<List<MessageConstructor.Button>> result = new ArrayList<>();

        int endOfRowElementIndex = 0;
        for (int buttonsInRow : buttonsMarkup) {
            String[] stringButtonsRow = Arrays.copyOfRange(buttons, endOfRowElementIndex, endOfRowElementIndex + buttonsInRow * 2);
            result.add(constructButtonsRow(stringButtonsRow));
            endOfRowElementIndex += stringButtonsRow.length;
        }
        return result;
    }

    private List<MessageConstructor.Button> constructButtonsRow(String[] stringButtonsRow) {
        List<MessageConstructor.Button> buttons = new ArrayList<>();
        for (int i = 0; i < stringButtonsRow.length; i += 2) {
            buttons.add(new MessageConstructor.Button(stringButtonsRow[i], stringButtonsRow[i + 1]));
        }
        return buttons;
    }
}
