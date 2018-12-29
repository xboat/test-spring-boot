package com.example.web.aspect;

import com.example.web.util.ServletUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;

/**
 * @author xboat date 2018-12-13
 */

@Aspect
@Component
@Order(100)
public class RequestLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut(value = "within(com.example.web..*) " +
        "&& (@annotation(org.springframework.web.bind.annotation.ResponseBody)" +
        "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)) ")
    //@Pointcut("execution(public * com.example.web.*.*(..))")
    private void pointcut() {

    }

    /**
     * 拦截
     *
     * @param joinPoint
     * @return
     */
   @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest req = ServletUtil.getRequest();
        RequestModel requestModel = new RequestModel(req);

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);

        if (apiOperation != null) {
            requestModel.setApiDesc(apiOperation.value());
        }

        Object proceed = joinPoint.proceed();
       requestModel.setResponseTime(OffsetDateTime.now());
       logger.info("RequestLogTestAspect request------->{}", requestModel.toString());
        return proceed;
    }

    private class RequestModel{

        private String url;

        private String method;

        private String remoteHost;

        private int remotePort;

        private OffsetDateTime requestTime = OffsetDateTime.now();

        private OffsetDateTime responseTime;

        private String apiDesc;

        RequestModel(HttpServletRequest request){
            this.remoteHost = request.getRemoteHost();
            this.remotePort = request.getRemotePort();
            this.url = request.getRequestURL().toString();
            this.method = request.getMethod();
        }

        public void setApiDesc(String apiDesc){
            this.apiDesc = apiDesc;
        }

        public void setResponseTime(OffsetDateTime responseTime){
            this.responseTime = responseTime;
        }
        @Override
        public String toString(){
            return String.format("{\"host\":\"%s\",\"port\":\"%s\",\"url\":\"%s\",\"method\":\"%s\",\"apiDesc\":\"%s\",\"requestTime\":\"%s\",\"responseTime\":\"%s\"}",this.remoteHost,this.remotePort,this.url,this.method,this.apiDesc,this.requestTime.toString(),this.responseTime.toString());
        }
    }
}
