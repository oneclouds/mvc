package com.ssm.Exceptions;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/9.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        CustomException customException = null;
        if(e instanceof CustomException){
//            系统自定义异常，
            customException=(CustomException) e;
        }else{
//            非系统自定义的异常，构造一个自定义异常类型（信息为“未知错误”）
            customException = new CustomException("位置错误");
        }
//        错误信息
        String message = customException.getMessage();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",message);
        modelAndView.setViewName("/err");
        return modelAndView;
    }
}
