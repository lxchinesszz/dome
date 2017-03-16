package com.example.config;

import com.example.web.PoolConnectionRest;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.example.config
 * @Description: 抛出的异常统一处理
 * @author: liuxin
 * @date: 17/3/6 下午2:02
 */
@ControllerAdvice(basePackageClasses = PoolConnectionRest.class)
public class PoolConnectionRestAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity(new CustoError("一不小心，程序走失了...", 500,new Gson().toJson(request.getParameterMap())), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    class CustoError {
        String msg;
        int code;
        Object data;

        CustoError(String msg, int code,Object object) {
            this.msg = msg;
            this.code = code;
            this.data=object;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}

