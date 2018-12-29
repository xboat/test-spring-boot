package com.example.web.controller;

import com.example.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author xboat date 2018-12-13
 */
@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class ValidControllerTest {

    @Autowired
    private ValidController controller;

    @Test
    public void test1() {
        controller.check(1);

    }
}
