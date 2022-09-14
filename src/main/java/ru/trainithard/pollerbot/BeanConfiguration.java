package ru.trainithard.pollerbot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trainithard.pollerbot.service.command.Command;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Configuration
public class BeanConfiguration {
    @Bean
    public Map<CommandName, Command> commands(List<Command> commands) {
        return commands.stream()
                .collect(toMap(Command::getCommandName, command -> command));
    }
}
