package ru.trainithard.pollerbot.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class RegularUserSession extends AbstractSession {
}
