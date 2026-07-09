package com.app.quantitymeasurement.util;

import com.app.quantitymeasurement.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionPool {

    private static final Logger logger =
            Logger.getLogger(
                    ConnectionPool.class.getName()
            );

    private static Connection connection;

    private ConnectionPool() {
    }

    public static Connection getConnection() {

        try {

            if (connection == null
                    || connection.isClosed()) {

                logger.info(
                        "Creating new database connection."
                );

                Class.forName(
                        ApplicationConfig.getProperty(
                                "db.driver"
                        )
                );

                connection = DriverManager.getConnection(

                        ApplicationConfig.getProperty(
                                "db.url"
                        ),

                        ApplicationConfig.getProperty(
                                "db.username"
                        ),

                        ApplicationConfig.getProperty(
                                "db.password"
                        )
                );
            }

            return connection;

        } catch (
                ClassNotFoundException
                | SQLException exception
        ) {

            logger.severe(
                    "Unable to establish database connection."
            );

            throw new DatabaseException(

                    "Unable to establish database connection",

                    exception
            );
        }
    }

    public static void closeConnection() {

        try {

            if (connection != null
                    && !connection.isClosed()) {

                connection.close();

                logger.info(
                        "Database connection closed."
                );
            }

        } catch (SQLException exception) {

            logger.severe(
                    "Unable to close database connection."
            );

            throw new DatabaseException(

                    "Unable to close database connection",

                    exception
            );
        }
    }
}