package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.CommandNameReplyRepository;
import ru.trainithard.pollerbot.repository.MessageKeyboardRepository;
import ru.trainithard.pollerbot.service.SessionService;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

import static ru.trainithard.pollerbot.util.MessageConstructor.Button;

public abstract class AbstractCommand {
    @Autowired
    protected MessageConstructor messageConstructor;
    @Autowired
    protected UserService userService;
    @Autowired
    protected SessionService sessionService;
    @Autowired
    protected CommandNameReplyRepository commandNameReplyRepository;
    @Autowired
    protected MessageKeyboardRepository messageKeyboardRepository;

    /**
     * Executes command based on UserMessage data.
     *
     * @param userMessage containing Update and User information
     * @return BotApiMethodMessage used for reply after command execution
     */
    public abstract BotApiMethodMessage execute(UserMessage userMessage);

    protected Long getUserId(UserMessage userMessage) {
        return userMessage.getUserId();
    }

    /**
     * Used for lookup of corresponding command. Should be added to CommandName enum.
     *
     * @return CommandName
     */
    public abstract CommandName getCommandName();

    protected boolean isFirstInvocation(UserMessage userMessage) {
        return userMessage.getSession().getPreviousCommandName() != getCommandName();
    }

    protected void saveSessionPreviousCommand(UserMessage userMessage) {
        userMessage.setPreviousCommandName(getCommandName());
        saveSession(userMessage);
    }

    protected void saveUserSession(UserMessage userMessage) {
        userService.save(getUser(userMessage));
        sessionService.save(userMessage.getUserId(), userMessage.getSession());
    }

    protected void saveUser(UserMessage userMessage) {
        userService.save(userMessage.getUser());
    }

    protected void saveSession(UserMessage userMessage) {
        sessionService.save(userMessage.getUserId(), userMessage.getSession());
    }

    protected SendMessage getTextMessage(UserMessage userMessage) {
        return messageConstructor.constructText(userMessage.getChatId(), commandNameReplyRepository.findText(getCommandName()));
    }

    protected SendMessage getTextButtonMessage(UserMessage userMessage) {
        return messageConstructor.constructTextButtons(userMessage,
                commandNameReplyRepository.findText(getCommandName()),
                getCommandNameButtons());
    }

    protected SendMessage getCustomTextButtonMessage(UserMessage userMessage, String text) {
        return messageConstructor.constructTextButtons(userMessage, text,
                getCommandNameButtons());
    }

    protected SendMessage getCustomButtonTextMessage(UserMessage userMessage, List<List<Button>> buttons) {
        return messageConstructor.constructTextButtons(userMessage,
                commandNameReplyRepository.findText(getCommandName()), buttons);
    }

    private List<List<Button>> getCommandNameButtons() {
        return commandNameReplyRepository.findButtons(getCommandName());
    }


    protected SendMessage getCustomTextMessage(UserMessage userMessage, String text) {
        return messageConstructor.constructText(userMessage.getChatId(), text);
    }

    protected SendMessage getErrorMessage(UserMessage userMessage) {
        return messageConstructor.constructText(userMessage.getChatId(), commandNameReplyRepository.findErrorText(getCommandName()));
    }

    protected User getUser(UserMessage userMessage) {
        return userMessage.getUser();
    }

    protected User getFreshUser(UserMessage userMessage) {
        return userService.find(userMessage.getUserId());
    }

    protected SendMessage getStandardMessage(UserMessage userMessage) {
        return messageConstructor.constructTextButtons(userMessage, messageKeyboardRepository.find(getCommandName()));
    }
}
