<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/10
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登陆</title>
</head>
<body>

<%--这里需要注意的是：form表单提交要用post方法，不然会出现乱码，
针对post乱码：这里在web.xml中进行了过滤
针对get方法：只能通过类似 new String(request.getParamter("username").getBytes("ISSO88859-1"),"utf-8")

要用get的话，只能通过--%>
<form action="${pageContext.request.contextPath}/login.action" method="post">
    用户账号:<input type="text" name="username"/><br/>
    用户密码:<input type="password" name="password"/><br/>
</form>
</body>
</html>
