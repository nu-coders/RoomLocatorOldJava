package com.nucoders.roomLocator.service;

import com.nucoders.roomLocator.database.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@PropertySource("application.properties")
@Service
public class service {

    private final database _database;

    @Autowired
    public service(database _database) {
        this._database = _database;
    }

    public List<String> b1Rooms() {
        return _database.buildingQuery("b1");
    }

    public List<String> b2Rooms() {
        return _database.buildingQuery("b2");
    }

    public List<String> rooms() {
        List<String> b1 = _database.buildingQuery("b1");
        List<String> b2 = _database.buildingQuery("b2");
        b1.addAll(b2);
        return b1;
    }

    public List<String> room(String roomName) {
        return _database.roomQuery(roomName);
    }

    public List<String> roomTable(String roomName) {
        return _database.tableQuery(roomName);
    }

}
