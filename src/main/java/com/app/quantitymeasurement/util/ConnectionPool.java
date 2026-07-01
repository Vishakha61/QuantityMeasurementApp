package com.app.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ConnectionPool {

    private static final Queue<Connection> availableConnections = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    private static final int POOL_SIZE = DatabaseConfig.getPoolSize();

    static {

        try {

            for (int i = 0; i < POOL_SIZE; i++) {

                Connection connection = DriverManager.getConnection(
                        DatabaseConfig.getUrl(),
                        DatabaseConfig.getUsername(),
                        DatabaseConfig.getPassword());

                availableConnections.offer(connection);
            }

           logger.info("Connection Pool initialized with {} connections.", POOL_SIZE);

        } catch (SQLException e) {
            throw new RuntimeException("Unable to initialize Connection Pool.", e);
        }
    }

    public static synchronized Connection getConnection() throws SQLException {

        if (availableConnections.isEmpty()) {
            throw new SQLException("No available database connections.");
        }

        return availableConnections.poll();
    }

    public static synchronized void releaseConnection(Connection connection) {

        if (connection != null) {
            availableConnections.offer(connection);
        }
    }

    public static synchronized int getAvailableConnections() {
        return availableConnections.size();
    }

    public static synchronized int getTotalConnections() {
        return POOL_SIZE;
    }

    public static synchronized String getPoolStatistics() {

        return "Total Connections : "
                + getTotalConnections()
                + ", Available Connections : "
                + getAvailableConnections();
    }

    public static synchronized void shutdown() {

        while (!availableConnections.isEmpty()) {

            try {

                availableConnections.poll().close();

            } catch (SQLException e) {
                logger.error("Error while closing connection.", e);
            }
        }
        
        logger.info("Connection Pool closed successfully.");
    }
}