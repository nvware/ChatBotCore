package org.nvware.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
//@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public String getActiveProfile() {
        return activeProfile;
    }

    @Value("${app.debug}")

    private String debug;
    @Value("${app.pathToLogs}")
    private String pathToLogs;

    public String getDebug() {
        return debug;
    }

    public String getPathToLogs() {
        return pathToLogs;
    }

    ///////////////DB param
    @Value("${db.linkDB}")
    private String linkDB;

    @Value("${db.controllerDB}")
    private String controllerDB;

    @Value("${db.userDB}")
    private String userDB;

    @Value("${db.passwordDB}")
    private String passwordDB;
    // Define bean creation methods or use the properties directly

    public String getLinkDB() {
        return linkDB;
    }

    public String getControllerDB() {
        return controllerDB;
    }

    public String getUserDB() {
        return userDB;
    }

    public String getPasswordDB() {
        return passwordDB;
    }
}
