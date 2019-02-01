package com.example.shardingjdbc.mapper;

import com.example.shardingjdbc.model.Order;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author xboat date 2019-01-31
 */
public interface OrderMapper extends Mapper<Order> {
    int inserTest(Order order);
}
