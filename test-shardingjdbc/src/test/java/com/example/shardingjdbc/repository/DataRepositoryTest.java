package com.example.shardingjdbc.repository;

import com.example.shardingjdbc.ShardingjdbcApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ShardingjdbcApplication.class)
public class DataRepositoryTest {

    @Autowired
    private DataRepository repository;

    @Test
    public void createTable() throws SQLException {
        repository.createTable();
    }

    @Test
    public void insertData() throws SQLException {
        repository.insertData();
    }

    @Test
    public void dropTable() throws SQLException {
        repository.dropTable();
    }
}