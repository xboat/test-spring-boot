package com.example.web.handler;

import com.example.web.common.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;


/**
 * @author xboat date 2018-12-10
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Result<Void> exceptionHandler(Exception e) {
        Result<Void> result = new Result<Void>();
        result.setStatus(false);
        if(e instanceof MissingServletRequestParameterException){
            result.setStatusCode("406");
            result.setMessage("访问参数异常");
        }else if(e instanceof BindException){
            StringBuilder strBuilder = new StringBuilder();
            BindException  bindException= (BindException) e;
            BindingResult bindingResult = bindException.getBindingResult();
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                strBuilder.append(error.getDefaultMessage() + "\n");
            }
            result.setStatusCode("406");
            result.setMessage(strBuilder.toString());
            return result;
        } else if(e instanceof MethodArgumentNotValidException){
            StringBuffer strBuilder = new StringBuffer();
            MethodArgumentNotValidException c = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = c.getBindingResult().getAllErrors();
            for (ObjectError error : errors) {
                strBuilder.append(error.getDefaultMessage()).append("\n");
            }
            result.setStatusCode("406");
            result.setMessage(strBuilder.toString());
        }
        else if(e instanceof ConstraintViolationException){
            ConstraintViolationException constraintViolationException= (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            StringBuilder strBuilder = new StringBuilder();
            for (ConstraintViolation<?> violation : violations ) {
                strBuilder.append(violation.getMessage()).append("\n");;
            }
            result.setStatusCode("406");
            result.setMessage(strBuilder.toString());
            return result;
        }
        else {
            result.setStatusCode("001");
            result.setMessage("未知异常，请联系系统管理员");
        }
        return result;
    }
}
