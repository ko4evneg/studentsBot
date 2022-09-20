package ru.trainithard.pollerbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Configuration
public class BeanConfiguration {
    @Value(value = "#{environment.dbPassword}")
    private String datasourcePassword;
    @Value(value = "#{environment.dbUser}")
    private String datasourceUsername;
    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String datasourceDriver;

    @Bean
    public Map<CommandName, AbstractCommand> commands(List<AbstractCommand> commands) {
        return commands.stream()
                .collect(toMap(AbstractCommand::getCommandName, command -> command));
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(datasourceDriver);
        dataSourceBuilder.url(datasourceUrl);
        dataSourceBuilder.username(datasourceUsername);
        dataSourceBuilder.password(datasourcePassword);
        return dataSourceBuilder.build();
    }
}
