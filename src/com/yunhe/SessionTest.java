package com.yunhe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //添加session
        //创建session对象 ，并设置值
        HttpSession session = req.getSession();
        session.setAttribute("username","zhangsan");
        //获取session
        Object username = session.getAttribute("username");
        System.out.println(username);
        //删除session
        //1.将指定session的value设置为null
        session.setAttribute("username",null);
        //2.移除指定key的session
        session.removeAttribute("username");
        //3.销毁当前的session
        session.invalidate();

        String id = session.getId();//得到session的id
    }
}
