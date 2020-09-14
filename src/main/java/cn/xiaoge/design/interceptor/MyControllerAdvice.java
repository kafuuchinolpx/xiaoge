package cn.xiaoge.design.interceptor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * 全局异常捕获
 */
@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("有一个异常已捕获:" + dateNowStr + mv);
        return mv;
    }
}

