package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.MessageKeyboardRepository;
import ru.trainithard.pollerbot.service.SessionService;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.dto.MessageKeyboard;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.MessageConstructor;

public abstract class AbstractCommand {
    @Autowired
    protected MessageConstructor messageConstructor;
    @Autowired
    protected UserService userService;
    @Autowired
    protected SessionService sessionService;
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

    protected User getUser(UserMessage userMessage) {
        return userMessage.getUser();
    }

    protected User getFreshUser(UserMessage userMessage) {
        return userService.find(userMessage.getUserId());
    }

    protected SendMessage getStandardTextMessage(UserMessage userMessage) {
        return messageConstructor.constructText(userMessage.getChatId(), getMessageKeyboard().getMessage());
    }

    protected SendMessage getStandardMessage(UserMessage userMessage) {
        return messageConstructor.constructTextButtons(userMessage, getMessageKeyboard());
    }

    protected SendMessage getErrorMessage(UserMessage userMessage) {
        return messageConstructor.constructErrorText(userMessage, getMessageKeyboard());
    }

    protected SendMessage getCustomMessage(UserMessage userMessage, String text) {
        return messageConstructor.constructCustomTextButtons(userMessage, getMessageKeyboard(), text);
    }

    protected SendMessage getCustomButtonsMessage(UserMessage userMessage, int[] buttonsMarkup, String...buttons) {
        return messageConstructor.constructCustomTextButtons(userMessage, getMessageKeyboard(), buttonsMarkup, buttons);
    }

    private MessageKeyboard getMessageKeyboard() {
        return messageKeyboardRepository.find(getCommandName());
    }
}
