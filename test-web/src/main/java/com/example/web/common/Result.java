package com.example.web.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xboat date 2018-12-10
 */

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean status = false;

    private String message;

    private T result;

    private String statusCode;

    public static <T> Result<T>  success(T data){
        return new Result<>(true,"操作成功",data,"200");
    }

    public static Result error(String statusCode,String message){
        return new Result(message,null,statusCode);
    }

    public Result() {
        super();
    }

    public Result(String message, T result, String statusCode) {
        this.message = message;
        this.result = result;
        this.statusCode = statusCode;
    }

    public Result(boolean status, String message, T result, String statusCode) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.statusCode = statusCode;
    }

}
