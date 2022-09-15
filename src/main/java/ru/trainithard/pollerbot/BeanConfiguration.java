package ru.trainithard.pollerbot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Configuration
public class BeanConfiguration {
    @Bean
    public Map<CommandName, AbstractCommand> commands(List<AbstractCommand> commands) {
        return commands.stream()
                .collect(toMap(AbstractCommand::getCommandName, command -> command));
    }
}
