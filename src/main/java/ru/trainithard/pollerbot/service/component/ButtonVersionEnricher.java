package ru.trainithard.pollerbot.service.component;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

@Component
public class ButtonVersionEnricher {
    public MessageConstructor.Button enrich(MessageConstructor.Button button, UserMessage userMessage) {
        button.setVersion(userMessage.getSessionVersion());
        return button;
    }

    public List<List<MessageConstructor.Button>> enrich(List<List<MessageConstructor.Button>> buttons, long sessionVersion) {
        buttons.forEach(buttonsRow -> enrichRow(buttonsRow, sessionVersion));
        return buttons;
    }

    private void enrichRow(List<MessageConstructor.Button> buttons, long sessionVersion) {
        buttons.forEach(button -> button.setVersion(sessionVersion));
    }
}
