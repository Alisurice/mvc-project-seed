<%@page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>${userType}</title>
</head>
<body>
<h2>Login</h2>


<form action="${loginApi}" method="post">
    用户名：<input type="text" name="username"><br><br>
    密  码：<input type="text" name="password"><br><br>
    <input type="submit" value="提交">
</form>

<%
    String loginStatus = (String)session.getAttribute("loginStatus");
%>
<c:if test="${loginStatus != null}">
    登录情况：<%=loginStatus%>
</c:if>
</body>
</html>
