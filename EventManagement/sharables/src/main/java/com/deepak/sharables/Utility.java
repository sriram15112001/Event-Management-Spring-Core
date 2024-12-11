package com.deepak.sharables;

import java.sql.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utility <T>{
    private final String JDBC_URL;
    private final String JDBC_USERNAME;
    private final String JDBC_PASSWORD;
    private final String DRIVER_CLASS;

    public Utility(String JDBC_URL, String JDBC_USERNAME, String JDBC_PASSWORD, String driverClass){
        this.JDBC_URL = JDBC_URL;
        this.JDBC_USERNAME = JDBC_USERNAME;
        this.JDBC_PASSWORD = JDBC_PASSWORD;
        this.DRIVER_CLASS = driverClass;
    }

    public void execute(String query, Object... args){
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(int i = 0; i < args.length; i++){
                preparedStatement.setObject(i + 1, args[0]);
            }
            preparedStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void execute(String query, Consumer<PreparedStatement> preparedStatementConsumer){
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatementConsumer.accept(preparedStatement);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public int executeUpdate(String query, Object... args){
        try{
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(int i = 0; i < args.length; i++){
                preparedStatement.setObject(i+1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public T findOne(String query, Function<ResultSet, T> mapper, Object... args){
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(int i = 0; i < args.length; i++){
                preparedStatement.setObject(i+1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return mapper.apply(resultSet);
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public List<T> findAll(String query, Function<ResultSet, List<T>> mapper, Object... args){
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(int i = 0; i < args.length; i++){
                preparedStatement.setObject(i+1, args[0]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> values = mapper.apply(resultSet);
            connection.close();
            return values;
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

}
