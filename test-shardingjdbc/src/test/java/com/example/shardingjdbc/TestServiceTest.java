package com.example.shardingjdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ShardingjdbcApplication.class)
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void insert() {
        testService.insert();
        System.out.println("插入成功");
    }
    @Test
    public void getDbNum() {
       for(Integer i=1;i<10;i++){
           System.out.println(i%3);
       }
    }
}