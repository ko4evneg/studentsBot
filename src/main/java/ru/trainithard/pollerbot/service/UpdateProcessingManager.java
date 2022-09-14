package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.service.component.CommandFinder;

@Service
@RequiredArgsConstructor
public class UpdateProcessingManager {
    private final CommandFinder commandFinder;

    public BotApiMethodMessage process(Update update) {
        return commandFinder.find(update).execute(update);
    }
}
