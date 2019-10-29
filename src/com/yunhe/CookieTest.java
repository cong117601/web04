package com.yunhe;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.yunhe.util.AiUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  AiUtil.dbDMLWithSQL("delete from student where id=5");
        // 创建cookie
        Cookie cookie = new Cookie("zhangsan", URLEncoder.encode("认为", "utf-8"));
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);
        //Cookie cookie1 = new Cookie("lisi", URLEncoder.encode("xiaob", "utf-8"));
        Cookie[] cookies = req.getCookies();
        System.out.println(cookies.length);
        for (Cookie c : cookies) {
            System.out.println(c.getName());
            System.out.println(c.getPath());
            System.out.println(c.getValue());//%E8%AE%A4%E4%B8%BA
        }
        //编码 解码
        String decode = URLDecoder.decode("%E8%AE%A4%E4%B8%BA", "utf-8");
        System.out.println(decode);
        //删除 cookie
        //1.设置访问的路径path
        //2.设置存活时间  为  0
        //3.响应添加cookie
//        Cookie coo = new Cookie("zhangsan", "");
//        coo.setPath(req.getContextPath());
//        coo.setMaxAge(0);
//        resp.addCookie(coo);


    }
}
