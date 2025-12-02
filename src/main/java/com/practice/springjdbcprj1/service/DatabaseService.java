package com.practice.springjdbcprj1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private DataSource dataSource;

    public List<String> listDatabases() {
        List<String> databases = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SHOW DATABASES")) {

            while (resultSet.next()) {
                databases.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            System.out.println("Database error: " + sqlException.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return databases;
    }

    public List<String> listTables(String database) {
        List<String> tables = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {

            statement.execute("USE " + database);
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            while (resultSet.next()) {
                tables.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            System.out.println("Database error: " + sqlException.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tables;
    }
}
