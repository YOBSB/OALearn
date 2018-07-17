package com.wenjun.oa.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangli0 on 2017/4/11.
 * github https://github.com/wangli0
 * blog http://www.jianshu.com/u/79a88a044955
 * website: http://need88.com
 */
@ControllerAdvice
public class CrashHandler {

    @ExceptionHandler({Exception.class})
    public ModelAndView handleXXException(Exception ex){
        System.out.println("=====handleXXException=====");
        ModelAndView mv = new ModelAndView("error");
        ex.printStackTrace();
        mv.addObject("ex",ex);
        return mv;
    }


}
