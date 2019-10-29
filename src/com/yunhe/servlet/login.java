package com.yunhe.servlet;

import com.yunhe.daoImpt.StudentDaoImpt;
import com.yunhe.javabean.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String sid = req.getParameter("user");
        String sword = req.getParameter("pwd");
        StudentDaoImpt sdi=new StudentDaoImpt();
        ArrayList<Student> students = sdi.selectWhereIdAndWord(sid,sword);
        System.out.println(students.size());
        if(students.size()==0){
            //登录失败
            req.setAttribute("msg", "账号或密码错误！");
            req.getRequestDispatcher("page/login.jsp").forward(req, resp);
        }else{
            //登录成功重定向至主页面服务
            resp.sendRedirect("main");
        }

    }
}
