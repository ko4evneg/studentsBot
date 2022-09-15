package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.CommandNameTextsRepository;
import ru.trainithard.pollerbot.service.SessionService;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

public abstract class AbstractCommand {
    @Autowired
    protected MessageConstructor messageConstructor;
    @Autowired
    protected UserService userService;
    @Autowired
    protected SessionService sessionService;
    @Autowired
    protected CommandNameTextsRepository commandNameTextsRepository;

    /**
     * Executes command based on UserMessage data.
     *
     * @param userMessage containing Update and User information
     * @return BotApiMethodMessage used for reply after command execution
     */
    public abstract BotApiMethodMessage execute(UserMessage userMessage);

    /**
     * Used for lookup of corresponding command. Should be added to CommandName enum.
     *
     * @return CommandName
     */
    public abstract CommandName getCommandName();

    protected boolean isFirstInvocation(UserMessage userMessage) {
        return userMessage.getSession().getPreviousCommandName() != getCommandName();
    }

    protected UserMessage shiftSessionToThisCommand(UserMessage userMessage) {
        getSession(userMessage).setPreviousCommandName(getCommandName());
        return userMessage;
    }

    protected void saveUserSession(UserMessage userMessage) {
        userService.save(userMessage.getUser());
        sessionService.save(userMessage.getUser(), userMessage.getSession());
    }

    protected SendMessage getTextMessage(UserMessage userMessage) {
        return messageConstructor.constructText(userMessage.getChatId(), commandNameTextsRepository.getText(getCommandName()));
    }

    protected SendMessage getTextButtonMessage(UserMessage userMessage, List<List<MessageConstructor.Button>> buttons) {
        return messageConstructor.constructTextButtons(userMessage.getChatId(),
                commandNameTextsRepository.getText(getCommandName()), buttons);
    }

    protected SendMessage getErrorMessage(UserMessage userMessage) {
        return messageConstructor.constructText(userMessage.getChatId(), commandNameTextsRepository.getErrorText(getCommandName()));
    }

    protected User getUser(UserMessage userMessage) {
        return userMessage.getUser();
    }

    protected Session getSession(UserMessage userMessage) {
        return userMessage.getSession();
    }
}
