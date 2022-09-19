package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryCommandNameReplyRepository implements CommandNameReplyRepository {
    private final Map<CommandName, String[]> replies = new HashMap<>();

    @Override
    public String findText(CommandName commandName) {
        return replies.get(commandName)[0];
    }

}
