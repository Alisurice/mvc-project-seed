<%@page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title text="客户注册"></title>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color: #111;
        }
    </style>
</head>
<body>
<ul>
    <li><a href="../index.html">登录</a></li>
    <li><a href="../register.html">注册</a></li>

</ul>

<h2>注册</h2>
<form action="../guest/register" method="post">
    用户名：<input type="text" name="username"><br><br>
    密  码：<input type="text" name="password"><br><br>
    <input type="submit" value="提交">
</form>

<%
    String resultMessage=(String)request.getAttribute("resultMessage");
%>
<p><%=resultMessage%></p>
<!--<div replace="WEB-INF/templates/list.html::copy">adsf</div>-->
<!--<div replace="~{list::copy}">adsf</div>-->

</body>

</html>
