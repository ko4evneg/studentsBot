package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

@Component
@RequiredArgsConstructor
public class ResetCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        updateUserSession.getSession().resetSession();
        saveSession(updateUserSession);
        return messageConstructor.construct(updateUserSession.getChatId(), "Сессия сброшена!");
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.RESET_COMMAND;
    }
}
