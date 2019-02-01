package com.example.shardingjdbc.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xboat date 2018-12-18
 */
@Data
@Table(name = "t_order")
public class Order implements Serializable {


    @Id
   // @GeneratedValue(generator = "JDBC")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "status")
    private String status;

}
