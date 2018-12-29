package com.example.web.exception;

import lombok.Data;

/**
 * @author xboat date 2018-12-13
 */

@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -1;

    private String code;

    private String msg;

    protected BaseException() {
    }

    protected BaseException(String code, String msg) {
        super(code+"="+msg);
        this.code = code;
        this.msg = msg;
    }

    protected BaseException(String code, String msg,Throwable e) {
        super(msg,e);
        this.code = code;
        this.msg = msg;
    }
}
