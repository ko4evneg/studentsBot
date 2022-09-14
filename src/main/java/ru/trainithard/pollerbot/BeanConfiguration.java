package ru.trainithard.pollerbot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.trainithard.pollerbot.service.command.Command;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Configuration
public class BeanConfiguration {
    @Bean
    @Primary
    public Map<String, Command> commands(List<Command> commands) {
        return commands.stream()
                .collect(toMap(Command::getCommandName, command -> command));
    }

    @Bean
    public Map<String, List<Command>> sessionCommands(List<Command> commands) {
        return commands.stream()
                .collect(groupingBy(Command::getSessionClassName,
                        collectingAndThen(toList(), this::getStepSortedCommands)));
    }

    private List<Command> getStepSortedCommands(List<Command> list) {
        list.sort(Comparator.comparing(Command::getStepNumber));
        return list;
    }
}
