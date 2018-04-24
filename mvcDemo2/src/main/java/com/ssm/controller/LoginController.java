package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/4/10.
 */
@Controller
public class LoginController {
//    登陆
    @RequestMapping("/login")
    public  String  login(HttpSession httpSession,String username,String password)throws  Exception {

//        调用service方法实现用户身份认证
//        .....
//

//        在session中保存用户身份信息
        httpSession.setAttribute("username",username);

//        重定向到商品列表页面
        return "redirect:/items/queryItems.action";
    }

//    退出
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) throws  Exception {

//        清楚session
        httpSession.invalidate();
//       重定向到商品列表页面
        return "redirect:/items/queryItems.action";
    }
}


