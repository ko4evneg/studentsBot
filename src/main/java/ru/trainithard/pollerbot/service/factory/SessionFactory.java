package ru.trainithard.pollerbot.service.factory;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.NewUserSession;
import ru.trainithard.pollerbot.model.Session;

@Component
public class SessionFactory {
    @Autowired
    private ObjectFactory<NewUserSession> newUserSessionFactory;

    public Session getNewUserSession(){
        return newUserSessionFactory.getObject();
    }
}
