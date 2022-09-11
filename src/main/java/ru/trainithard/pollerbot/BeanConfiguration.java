package ru.trainithard.pollerbot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trainithard.pollerbot.service.command.Command;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class BeanConfiguration {
    @Bean
    public Map<CommandName, Command> commands(List<Command> commandsList){
        return commandsList.stream()
                .collect(Collectors.toMap(Command::getCommandName, command -> command));
    }
}
