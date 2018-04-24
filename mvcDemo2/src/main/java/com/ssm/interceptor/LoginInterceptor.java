package com.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/4/10.
 */
public class LoginInterceptor implements HandlerInterceptor {

//    进入Handler方法之前执行
//    用于身份认证、身份授权
//    比如身份认证，如果认证不通过表示当前用户没有登陆，需要此方法拦截不在向下执行
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

//        获取请求的url
        String url = httpServletRequest.getRequestURI();
//        判断url是否是公开地址（实际使用时将公开地址配置在配置文件中）
//        这里公开地址是登录提交的地址
        if (url.indexOf("login.action")>0) {
//            如果进行登陆提交，就放行
            return true;
        }

//        判断session
        HttpSession httpSession = httpServletRequest.getSession();
//        从httpSession中取出用户信息
        String username=(String) httpSession.getAttribute("username");
        if (username!=null) {
//            身份存在，就放行
            return true;
        }

//        执行到这里表示用户身份需要认证，跳转登陆页面
        httpServletRequest.getRequestDispatcher("/jsp/login.jsp").forward(httpServletRequest,httpServletResponse);

//        return false 表示拦截，不向下执行
//        return true 表示执行
        return false;
    }


//    进入Handler方法之后，返回ModelAndView之前执行
//    应用场景从modelAndView出发，将共用的模型数据（比如菜单导航的数据）在这里传入视图，也可以在这里统一制定视图
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

//    执行Handler完成执行此方法
//    应用场景：统一异常处理，统一日志处理
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
