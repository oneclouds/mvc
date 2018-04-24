<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
当前用户：${username}
<c:if test="${username!=null}">
    <a href="${pageContext.request.contextPath}/logout.action">退出</a>
</c:if>
<table width="100%" border="1">
    <tr>
        <td>商品名称</td>
        <td>商品价格</td>
        <td>生产日期</td>
        <td>商品描述</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${itemsList}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            <td>${item.detail}</td>
            <td><a href="${pageContext.request.contextPath}/items/editItems.action?id=${item.id}">修改</a></td>
        </tr>
    </c:forEach>



</table>
</body>
</html>
