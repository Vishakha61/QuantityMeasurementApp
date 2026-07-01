package com.app.quantitymeasurement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConfig {

    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties file not found.");
            }

            properties.load(input);

            // Load JDBC Driver
            Class.forName(getDriver());

        } catch (IOException e) {
            throw new RuntimeException("Unable to load application.properties", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database Driver not found.", e);
        }
    }

    public static String getDriver() {
        return properties.getProperty("db.driver");
    }

    public static String getUrl() {
        return properties.getProperty("db.url");
    }

    public static String getUsername() {
        return properties.getProperty("db.username");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }

    public static int getPoolSize() {
        return Integer.parseInt(
                properties.getProperty("db.pool.size", "10"));
    }

    public static String getRepositoryType() {
        return properties.getProperty("repository.type", "database");
    }
}