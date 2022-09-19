package ru.trainithard.pollerbot.util;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.dto.MessageKeyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.trainithard.pollerbot.util.MessageConstructor.*;

@Component
public class ButtonConstructor {

    /**
     * @param messageKeyboard which must contain row markup and strings of buttons
     * @return List&lt;List&lt;Button&gt;&gt; list of rows, each row containing buttons
     */
    public List<List<Button>> constructButtons(MessageKeyboard messageKeyboard) {
        List<List<Button>> result = new ArrayList<>();

        int endOfRowElementIndex = 0;
        for (int buttonsInRow : messageKeyboard.getButtonInRowsMarkup()) {
            String[] stringButtonsRow = Arrays.copyOfRange(messageKeyboard.getButtonRows(),
                    endOfRowElementIndex, endOfRowElementIndex + buttonsInRow * 2);
            result.add(constructButtonsRow(stringButtonsRow));
            endOfRowElementIndex += stringButtonsRow.length;
        }
        return result;
    }

    private List<Button> constructButtonsRow(String[] stringButtonsRow) {
        List<Button> buttons = new ArrayList<>();
        for (int i = 0; i < stringButtonsRow.length; i += 2) {
            buttons.add(new Button(stringButtonsRow[i], stringButtonsRow[i + 1]));
        }
        return buttons;
    }
}
