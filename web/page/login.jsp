<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2019/10/14
Time: 18:19
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8"
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/login">   <!--/web04_war_exploded-->
    账号：<input type="text" name="user" placeholder="请在此输入账号"><br>
    密码：<input type="password" name="pwd" placeholder="请在此输入密码"><br>
    <input type="submit" value="登录">
    <font color="red"><%  if(request.getAttribute("msg")!=null){out.print(request.getAttribute("msg"));} %></font>
</form>
</body>
</html>
