package com.example.web.controller;

import com.example.web.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author xboat date 2018-12-10
 */
@Api("mvc测试")
@RestController
@RequestMapping("/api")
@Validated
public class ValidController {
    //方式一
    @ApiOperation("方式一")
    @ApiImplicitParam(paramType="query",name = "age", value = "年龄", dataType = "int")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<Integer> check(@Min(value = 2,message = "age必须大于2") int age) {
        Result result = new Result();
        result.setResult(age);
        result.setMessage("真是年龄");
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@NotBlank(message = "name 不能等于空") String name, BindingResult bindingResult) {
        return "m------->" + name;
    }

    // 方式二
    @ApiOperation("方式二")
    @ApiImplicitParam(paramType="body",name = "requestParam", value = "请求参数", dataType = "RequestParam")
    @RequestMapping(value = "/check1", method = RequestMethod.GET)
    public Result<Integer> check1(@Validated RequestParam requestParam,BindingResult bindingResult){
        Result result = new Result();

        if (bindingResult.hasErrors()) {
            result.setResult(requestParam.age);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
        }
        else{
            result.setResult(requestParam.age);
            result.setMessage("年龄真实");
        }
        return result;
    }

    // 方式二
    @ApiOperation("方式三")
    @ApiImplicitParam(name = "requestParam", value = "请求参数", dataType = "RequestParam")
    @RequestMapping(value = "/check2", method = RequestMethod.POST)
    public int check2(@RequestBody @Valid RequestParam requestParam){
        return requestParam.getAge();
    }
}
