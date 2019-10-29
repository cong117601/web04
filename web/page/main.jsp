<%@ page import="com.yunhe.javabean.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/14
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
<%ArrayList<Student>  students=(ArrayList)request.getAttribute("students");

%>
<table>
    <tr><th>账号</th><th>密码</th><th>真实姓名</th><th>是否被点</th></tr>
    <%if(students!=null){
        for(Student s:students){%>
    <tr><td><%=s.getSid() %></td><td><%=s.getSword() %></td><td><%=s.getSname() %></td><td><%if(s.getIsTrue()==0){out.print("否");}else{out.print("是");} %></td></tr>
    <% }
    } %>

</table>




</body>


</body>
</html>
