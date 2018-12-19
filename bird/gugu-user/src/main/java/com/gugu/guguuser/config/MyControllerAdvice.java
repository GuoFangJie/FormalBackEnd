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
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {

    }

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception e, HttpServletResponse httpServletResponse) {
        e.printStackTrace();
        httpServletResponse.setStatus(500);
        Map map = new HashMap(1);
        map.put("code", 500);
        map.put("message", "System error");
        return map;
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
        Map map = new HashMap(1);
        map.put("code", 500);
        map.put("message", "Error on querying database");
        return map;
    }

    /**
     * 参数错误异常捕捉处理
     * @param e
     * @return Map
     */
    @ExceptionHandler(value = SQLException.class)
    public Map paramErrorHandler(ParamErrorException e, HttpServletResponse httpServletResponse) {
        e.printStackTrace();
        httpServletResponse.setStatus(400);
        Map map = new HashMap(1);
        map.put("code", 400);
        map.put("message", e.getErrorMsg());
        return map;
    }

    /**
     * 未找到资源异常捕捉处理
     * @param e
     * @return Map
     */
    @ExceptionHandler(value = SQLException.class)
    public Map paramErrorHandler(NotFoundException e, HttpServletResponse httpServletResponse) {
        e.printStackTrace();
        httpServletResponse.setStatus(404);
        Map map = new HashMap(1);
        map.put("code", 404);
        map.put("message", e.getErrorMsg());
        return map;
    }
}
