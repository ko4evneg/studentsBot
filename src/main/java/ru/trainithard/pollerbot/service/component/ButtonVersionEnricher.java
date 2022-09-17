package ru.trainithard.pollerbot.service.component;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

@Component
public class ButtonVersionEnricher {
    public List<List<MessageConstructor.Button>> enrich(List<List<MessageConstructor.Button>> buttons, long sessionVersion) {
        buttons.forEach(buttonsRow -> enrichRow(buttonsRow, sessionVersion));
        return buttons;
    }

    private void enrichRow(List<MessageConstructor.Button> buttons, long sessionVersion) {
        buttons.forEach(button -> button.setVersion(sessionVersion));
    }
}
