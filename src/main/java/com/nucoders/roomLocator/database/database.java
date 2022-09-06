package com.nucoders.roomLocator.database;


import com.nucoders.roomLocator.time.time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@PropertySource("application.properties")
@Component
public class database {

    private final Statement statement;
    private final time _time = new time();


    @Autowired
    public database(@Value("${database.url}") String databaseUrl, @Value("${database.driver}")
    String databaseDriver, @Value("${database.user}") String user, @Value("${database.password}") String password) {
        try {
            Class.forName(databaseDriver);
            Connection con = DriverManager.getConnection(databaseUrl, user, password);
            statement = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List buildingQuery(String building) {
        String day = _time.currentDay();
        String slotNumber = _time.currentSlot();
        String query = String.format("SELECT room FROM %srooms WHERE %s = TRUE AND day = '%s'", building, slotNumber, day);
        try {
            return setToList(statement.executeQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List roomQuery(String roomName) {
        String query;
        String day = _time.currentDay();
        String slotNumber = _time.currentSlot();

        if (Character.isAlphabetic(roomName.charAt(0)))
            query = String.format("SELECT %s FROM b2rooms WHERE day = '%s' AND room = '%s'", slotNumber, day, roomName);
        else
            query = String.format("SELECT %s FROM b1rooms WHERE day = '%s' AND room = '%s'", slotNumber, day, roomName);
        try {
            return setToList(statement.executeQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List tableQuery(String roomName) {
        String query;
        String day = _time.currentDay();
        String slots = "slot1,slot2,slot3,slot4,slot5,slot6,slot7,slot8,slot9,slot10,slot11,slot12";
        if (Character.isAlphabetic(roomName.charAt(0)))
            query = String.format("SELECT %s FROM b2rooms WHERE day = '%s' AND room = '%s'", slots, day, roomName);
        else
            query = String.format("SELECT %s FROM b1rooms WHERE day = '%s' AND room = '%s'", slots, day, roomName);
        try {
            return setToListSlots(statement.executeQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List setToList(ResultSet input) {
        List<String> output = new ArrayList<>();
        try {
            while (input.next()) {
                output.add(input.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public List setToListSlots(ResultSet input) {
        List<String> output = new ArrayList<>();
        int i = 1;
        try {
            while (input.next()) {
                while (i != 13) {
                    output.add(input.getString(i));

                    i++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
}

