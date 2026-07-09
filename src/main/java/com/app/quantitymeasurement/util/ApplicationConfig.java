package com.app.quantitymeasurement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ApplicationConfig {

    private static final Logger logger =
            Logger.getLogger(
                    ApplicationConfig.class.getName()
            );

    private static final Properties properties =
            new Properties();

    static {

        try (
                InputStream input =
                        ApplicationConfig.class
                                .getClassLoader()
                                .getResourceAsStream(
                                        "application.properties"
                                )
        ) {

            if (input == null) {

                throw new RuntimeException(
                        "application.properties not found"
                );
            }

            properties.load(input);

            logger.info(
                    "application.properties loaded successfully."
            );

        } catch (IOException exception) {

            logger.severe(
                    "Failed to load application.properties"
            );

            throw new RuntimeException(
                    "Unable to load configuration",
                    exception
            );
        }
    }

    private ApplicationConfig() {
    }

    public static String getProperty(
            String key
    ) {

        return properties.getProperty(
                key
        );
    }
}
