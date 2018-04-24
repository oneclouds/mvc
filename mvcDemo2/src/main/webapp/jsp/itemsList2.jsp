<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function deleteItems(){
            document.itemsForm.action = "${pageContext.request.contextPath}/items/deleteItems.action";
            document.itemsForm.submit();
        }
        function queryItems(){
            document.itemsForm.action = "${pageContext.request.contextPath}/items/queryItems.action";
            document.itemsForm.submit();
        }
    </script>


</head>
<body>
<%--显示错误信息--%>
<%--先做见状性判断--%>
<c:if test="${allErrors!=null}">
<c:forEach items="${allErrors}" var="error">
    ${error.defaultMessage}
</c:forEach>
</c:if>

<form name="itemsForm" action="${pageContext.request.contextPath}/items/queryItems.action" method="post" enctype="Multipart/form-data">
    查询条件：
    <table width="100%" border="1">
        <tr>
            <td>
                <%--这里按照商品名称进行查询，是将参数绑定到包装类型ItemsQueryVo的属性itemsCustom下的属性，也就是po类中包含了po类，即复杂 po类包装po类，其中包装类的属性直接写属性不用加包装类
                --%>
                商品名称：<input name="itemsCustom.name" value="查询">
                商品类型：
                <select name="">
                    <c:forEach items="${itemtypes}"> var="itemtype">
                        <<option value="${itemtype.key}">${itemtype.value}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="button" value="查询" onclick="queryItems()"></td>
            <td><input type="button" value="批量删除" onclick="deleteItems()"></td>
        </tr>

        <tr>
            <td>商品图片</td>
            <td>
                <c:if test="${item.pic!=null}">
                    <img src="/pic/${item.pic}" width="100" height="100">
                    <br/>
                </c:if>
            </td>
        </tr>

    </table>

    商品列表：
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
                <td><input type="checkbox" name="items_id" value="${item.id}"></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.detail}</td>
                <td><a href="${pageContext.request.contextPath}/items/editItems.action?id=${item.id}">修改</a></td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>
