package com.practice.springjdbcprj1.controller;

import com.practice.springjdbcprj1.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DatabaseController {
    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/databases")
    public List<String> getDatabases() {
        return databaseService.listDatabases();
    }

    @GetMapping("/databases/{dbName}/tables")
    public List<String> getTables(@PathVariable String dbName) {
        return databaseService.listTables(dbName);
    }
}
