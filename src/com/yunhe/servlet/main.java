package com.yunhe.servlet;

import com.yunhe.daoImpt.StudentDaoImpt;
import com.yunhe.javabean.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDaoImpt sdi=new StudentDaoImpt();
        ArrayList<Student> students = sdi.selectAll();
        req.setAttribute("students", students);
        req.getRequestDispatcher("page/main.jsp").forward(req, resp);
    }
}
