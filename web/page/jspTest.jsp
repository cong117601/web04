<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yunhe.javabean.Student" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/16
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<%--
page指令 设置页面相应属性
<%@include file="login.jsp"%>
include 导入其他界面
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
使用时需要导入两个jar包 （TL） 到Libraries下
用途就是 导入标签  c 是标签前缀
<jsp:forward page="login.jsp"></jsp:forward>
实现页面跳转标签   地址栏不变 ，其中不是跳转的地址栏地址
等价与 <%request.getRequestDispatcher("/one.jsp").forward(request, response); %>
<jsp:forward page="jspTest.jsp">
    <jsp:param value="name" name="1"/>
    <jsp:param value="value" name="2"/>
</jsp:forward>
param用于页面跳转时传递参数
<jsp:useBean id="Student" class="com.yunhe.javabean.Student"/>
<jsp:setProperty name="Student" property="sname" value="bb"></jsp:setProperty>
<jsp:getProperty name="Student" property="sname"/>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="s2" class="com.yunhe.javabean.Student" scope="page"/>
<jsp:setProperty name="s2" property="sname" value="xiaoz"/>
<jsp:getProperty name="s2" property="sname"/>
<html>
<head>
    <title>jspTest</title>

</head>
<body>
<%
    Student s = new Student("bb", "张三", "000000", 0);
    Student s1 = new Student();
    request.setAttribute("student", s);
    request.setAttribute("student1", s1);
    ArrayList arr = new ArrayList();
    arr.add(s);
    arr.add(s1);
    request.setAttribute("arr", arr);
%>
<c:set var="aaa" value="vvv" scope="request"/>
<c:set target="${student}" value="小白" property="sname"/>
<c:out value="${student}"/>
<c:out value="${aaa}"/>
<c:out value="${student.sname}"/>
<c:out value="${s1.sname}" default="没有数据"/><br>
<c:if test="${student.sname eq '小白'}" var="ccc" scope="request">
    <font>当前登录账号为管理员</font><br>
</c:if>
${s2.sname}
${s2["sname"]}

<!--  Choose标签 进行多级条件判断，当任意条件满足 将其下内容打印页面
当when中test返回true 时，在页面书写相应内容
当所有when都返回false 执行otherwise中内容至页面
if   else
-->
<c:choose>
    <c:when test="${student.isTrue==0}">
        <input type="radio" checked="checked">男
        <input type="radio">女
    </c:when>
    <c:when test="${student.isTrue==1}">
        <input type="radio">男
        <input type="radio" checked="checked">女
    </c:when>
    <c:otherwise>
        啥也没有
    </c:otherwise>
</c:choose>

<!- foreach 标签 用于数据的遍历以及循环操作
items 要遍历的数据 var保存数据的中间遍量
-->
<table>
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>性别</th>
    </tr>
    <c:forEach items="${arr}" var="a">
        <tr>
            <th><c:out value="${a.sid}" /></th>

        <th>${a.sname}</th>
        <th>
        <c:if test="${a.isTrue==0}">男</c:if>
        </th>
        </tr>
    </c:forEach>
<!--  用于循环
begin开始数据  end结束数据  step步数（数据增长量）
varstatus 声明保存当前循环状态变量名
count 表示当前遍历集合的元素个数 1,2,3，4
index 表示当前遍历到集合的第几个元素
current 表示当前的集合元素
first 表示是否是集合的第一个元素
last 表示是否是集合的最后一个元素
int i=2;i<10;i+=2
-->
    <c:forEach begin="2" end="10" step="2" varStatus="status">
        ${status.current}
    </c:forEach>
<!--
redirect标签   将页面重定向至  指定url
-->

    <!--
    url标签 用于定义http请求
    muURL 保存的内容 login.jsp?name=baidu&url=www.baidu.com
    -->
    <c:url var="myURL" value="login.jsp">
        <c:param name="name" value="baidu"/>
        <c:param name="url" value="www.baidu.com"/>
    </c:url>
</table>
</body>
</html>
