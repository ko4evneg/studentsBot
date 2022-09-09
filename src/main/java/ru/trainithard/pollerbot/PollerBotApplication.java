package ru.trainithard.pollerbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PollerBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollerBotApplication.class, args);
    }

}
