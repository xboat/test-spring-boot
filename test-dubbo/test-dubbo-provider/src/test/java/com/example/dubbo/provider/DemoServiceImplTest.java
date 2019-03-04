package com.example.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author xboat date 2019-‎02-‎20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderApplication.class)
public class DemoServiceImplTest {

    @Reference
    private DemoService demoService;

    @Test
    public void sayHello(){
        String str = demoService.sayHello("赵云");
        System.out.println(str);
    }

}