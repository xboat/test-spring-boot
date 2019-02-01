package com.example.shardingjdbc;

import com.example.shardingjdbc.mapper.OrderMapper;
import com.example.shardingjdbc.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private OrderMapper mapper;

    public void insert(){
//        for(int i = 0; i<=3; i++){
//            Order order = new Order();
//           // order.setOrderId(Long.valueOf(i));
//            order.setUserId(i);
//            order.setStatus("test_09"+i);
//            System.out.println(i);
//            mapper.inserTest(order);
//        }

        Order order = new Order();
        order.setOrderId(18L);
        order.setUserId(18);
        order.setStatus("test_18");
        mapper.inserTest(order);

    }
}
