package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Statement;
import java.sql.ResultSet;
public class QuantityMeasurementDatabaseRepository
        implements IQuantityRepository {

    private static QuantityMeasurementDatabaseRepository instance;

    private static final String INSERT_QUERY =
            "INSERT INTO quantity_measurement " +
            "(measurement_type,operation_type,value1,unit1,value2,unit2,result) " +
            "VALUES(?,?,?,?,?,?,?)";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM quantity_measurement";

    private static final String SELECT_BY_OPERATION_QUERY =
            "SELECT * FROM quantity_measurement WHERE operation_type=?";

    private static final String SELECT_BY_TYPE_QUERY =
            "SELECT * FROM quantity_measurement WHERE measurement_type=?";

    private static final String COUNT_QUERY =
            "SELECT COUNT(*) FROM quantity_measurement";

    private static final String DELETE_ALL_QUERY =
            "DELETE FROM quantity_measurement";

   private QuantityMeasurementDatabaseRepository() {
    initializeDatabase();
}
    public static synchronized
    QuantityMeasurementDatabaseRepository getInstance() {

        if (instance == null) {
            instance = new QuantityMeasurementDatabaseRepository();
        }

        return instance;
    }
        @Override
    public void save(
            QuantityMeasurementEntity entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection =
                    ConnectionPool.getConnection();

            preparedStatement =
                    connection.prepareStatement(
                            INSERT_QUERY);

            preparedStatement.setString(
                    1,
                    entity.getMeasurementType());

            preparedStatement.setString(
                    2,
                    entity.getOperation());

            preparedStatement.setDouble(
                    3,
                    entity.getThisValue());

            preparedStatement.setString(
                    4,
                    entity.getThisUnit());

            preparedStatement.setDouble(
                    5,
                    entity.getThatValue());

            preparedStatement.setString(
                    6,
                    entity.getThatUnit());

            preparedStatement.setString(
                    7,
                    entity.getResultString());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to save measurement.",
                    e);

        } finally {

            try {

                if (preparedStatement != null)
                    preparedStatement.close();

            } catch (SQLException ignored) {
            }

            ConnectionPool.releaseConnection(
                    connection);
        }

    }
private void initializeDatabase() {

    Connection connection = null;
    Statement statement = null;

    try {

        connection = ConnectionPool.getConnection();

        InputStream inputStream =
                getClass().getClassLoader()
                        .getResourceAsStream("db/schema.sql");

        if (inputStream == null) {
            throw new DatabaseException("schema.sql not found.");
        }

        String sql = new String(
                inputStream.readAllBytes(),
                StandardCharsets.UTF_8);

        statement = connection.createStatement();

        statement.execute(sql);

    } catch (Exception e) {

        throw new DatabaseException(
                "Unable to initialize database.",
                e);

    } finally {

        try {

            if (statement != null)
                statement.close();

        } catch (SQLException ignored) {
        }

        ConnectionPool.releaseConnection(connection);
    }
}
    @Override
public List<QuantityMeasurementEntity> getAllMeasurements() {

    List<QuantityMeasurementEntity> measurements =
            new ArrayList<>();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

        connection = ConnectionPool.getConnection();

        preparedStatement =
                connection.prepareStatement(
                        SELECT_ALL_QUERY);

        resultSet =
                preparedStatement.executeQuery();

        while (resultSet.next()) {

            measurements.add(
                    mapResultSet(resultSet));

        }

    } catch (SQLException e) {

        throw new DatabaseException(
                "Unable to fetch measurements.",
                e);

    } finally {

        try {

            if(resultSet!=null)
                resultSet.close();

            if(preparedStatement!=null)
                preparedStatement.close();

        } catch (SQLException ignored){}

        ConnectionPool.releaseConnection(connection);

    }

    return measurements;

}

@Override
public List<QuantityMeasurementEntity>
getMeasurementsByOperation(
        String operation) {

    List<QuantityMeasurementEntity> measurements =
            new ArrayList<>();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

        connection =
                ConnectionPool.getConnection();

        preparedStatement =
                connection.prepareStatement(
                        SELECT_BY_OPERATION_QUERY);

        preparedStatement.setString(
                1,
                operation);

        resultSet =
                preparedStatement.executeQuery();

        while(resultSet.next()){

            measurements.add(
                    mapResultSet(resultSet));

        }

    } catch (SQLException e) {

        throw new DatabaseException(
                "Unable to fetch operation history.",
                e);

    } finally {

        try{

            if(resultSet!=null)
                resultSet.close();

            if(preparedStatement!=null)
                preparedStatement.close();

        }catch(SQLException ignored){}

        ConnectionPool.releaseConnection(connection);

    }

    return measurements;

}

@Override
public List<QuantityMeasurementEntity>
getMeasurementsByType(
        String measurementType) {

    List<QuantityMeasurementEntity> measurements =
            new ArrayList<>();

    Connection connection=null;

    PreparedStatement preparedStatement=null;

    ResultSet resultSet=null;

    try{

        connection=
                ConnectionPool.getConnection();

        preparedStatement=
                connection.prepareStatement(
                        SELECT_BY_TYPE_QUERY);

        preparedStatement.setString(
                1,
                measurementType);

        resultSet=
                preparedStatement.executeQuery();

        while(resultSet.next()){

            measurements.add(
                    mapResultSet(resultSet));

        }

    }catch(SQLException e){

        throw new DatabaseException(
                "Unable to fetch measurement type.",
                e);

    }finally{

        try{

            if(resultSet!=null)
                resultSet.close();

            if(preparedStatement!=null)
                preparedStatement.close();

        }catch(SQLException ignored){}

        ConnectionPool.releaseConnection(connection);

    }

    return measurements;

}

private QuantityMeasurementEntity
mapResultSet(ResultSet rs)
        throws SQLException {

    QuantityMeasurementEntity entity =
            new QuantityMeasurementEntity();

    entity.setMeasurementType(
            rs.getString("measurement_type"));

    entity.setOperation(
            rs.getString("operation_type"));

    entity.setThisValue(
            rs.getDouble("value1"));

    entity.setThisUnit(
            rs.getString("unit1"));

    entity.setThatValue(
            rs.getDouble("value2"));

    entity.setThatUnit(
            rs.getString("unit2"));

    entity.setResultString(
            rs.getString("result"));

    return entity;

}

@Override
public int getTotalCount() {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

        connection = ConnectionPool.getConnection();

        preparedStatement = connection.prepareStatement(
                COUNT_QUERY);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;

    } catch (SQLException e) {

        throw new DatabaseException(
                "Unable to get total count.",
                e);

    } finally {

        try {

            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

        } catch (SQLException ignored) {
        }

        ConnectionPool.releaseConnection(connection);

    }

}

@Override
public void deleteAll() {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {

        connection = ConnectionPool.getConnection();

        preparedStatement =
                connection.prepareStatement(
                        DELETE_ALL_QUERY);

        preparedStatement.executeUpdate();

    } catch (SQLException e) {

        throw new DatabaseException(
                "Unable to delete records.",
                e);

    } finally {

        try {

            if (preparedStatement != null)
                preparedStatement.close();

        } catch (SQLException ignored) {
        }

        ConnectionPool.releaseConnection(connection);

    }

}

@Override
public String getPoolStatistics() {

    return ConnectionPool.getPoolStatistics();

}

@Override
public void releaseResources() {

    ConnectionPool.shutdown();

}

        }