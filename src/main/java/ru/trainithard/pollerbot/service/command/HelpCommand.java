package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

@Component
@RequiredArgsConstructor
public class HelpCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        return messageConstructor.construct(updateUserSession.getChatId(), "Помощь: \r\n" +
                "/reset - сброс сессии");
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
