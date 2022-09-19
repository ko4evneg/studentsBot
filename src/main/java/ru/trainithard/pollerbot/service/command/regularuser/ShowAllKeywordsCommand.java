package ru.trainithard.pollerbot.service.command.regularuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.service.LessonService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.component.ColumnOutputFormatter;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.util.Collections;
import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.SHOW_ALL_KEYWORDS;

@Component
@RequiredArgsConstructor
public class ShowAllKeywordsCommand extends AbstractCommand {
    private static final int OUTPUT_COLUMNS = 5;
    private final LessonService lessonService;
    private final ColumnOutputFormatter columnOutputFormatter;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        List<String> keywords = lessonService.findAllKeywords();
        Collections.sort(keywords);

        userMessage.setPreviousCommandName(getCommandName());
        saveSession(userMessage);

        SendMessage reply = getCustomMessage(userMessage, columnOutputFormatter.format(keywords, OUTPUT_COLUMNS));
        reply.setParseMode("Markdown");
        return reply;
    }

    @Override
    public CommandName getCommandName() {
        return SHOW_ALL_KEYWORDS;
    }
}
