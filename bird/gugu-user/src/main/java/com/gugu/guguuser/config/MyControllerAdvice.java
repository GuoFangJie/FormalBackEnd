package com.gugu.guguuser.config;

import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.exception.ParamErrorException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TYJ
 */
@RestControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     * @param e
     * @return Map
     */
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception e, HttpServletResponse httpServletResponse) {
        e.printStackTrace();
        httpServletResponse.setStatus(500);
        return this.getResult(500,"System error!");
    }

    /**
     * SQL异常捕捉处理
     * @param e
     * @return Map
     */
    @ExceptionHandler(value = SQLException.class)
    public Map queryErrorHandler(SQLException e,HttpServletResponse httpServletResponse) {
        e.printStackTrace();
        httpServletResponse.setStatus(500);
        return this.getResult(500,"error on querying database!");
    }

    /**
     * 参数错误异常捕捉处理
     * @param e
     * @return Map
     */
    @ExceptionHandler(value = ParamErrorException.class)
    public Map paramErrorHandler(ParamErrorException e, HttpServletResponse httpServletResponse) {
        e.printStackTrace();
        httpServletResponse.setStatus(400);
        return this.getResult(400,e.getErrorMsg());
    }

    /**
     * 未找到资源异常捕捉处理
     * @param e
     * @return Map
     */
    @ExceptionHandler(value = NotFoundException.class)
    public Map paramErrorHandler(NotFoundException e,HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(404);
        e.printStackTrace();
        return this.getResult(404,e.getErrorMsg());
    }

    /**
     * 获取错误信息
     * @param code
     * @param message
     * @return Map
     */
    private Map getResult(int code,String message){
        Map map = new HashMap(1);
        map.put("code", code);
        map.put("message", message);
        return map;
    }
}
