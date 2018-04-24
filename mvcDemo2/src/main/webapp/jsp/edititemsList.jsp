<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function editItemsAllSubmit(){
            document.itemsForm.action = "${pageContext.request.contextPath}/items/editItemsAllSubmit.action";
            document.itemsForm.submit();
        }
        function queryItems(){
            document.itemsForm.action = "${pageContext.request.contextPath}/items/queryItems.action";
            document.itemsForm.submit();
        }
    </script>


</head>
<body>

<form name="itemsForm" action="${pageContext.request.contextPath}/items/queryItems.action" method="post">
    查询条件：
    <table width="100%" border="1">
        <tr>
            <td>
                <%--这里按照商品名称进行查询，是将参数绑定到包装类型ItemsQueryVo的属性itemsCustom下的属性，也就是po类中包含了po类，即复杂 po类包装po类，其中包装类的属性直接写属性不用加包装类
                --%>
                商品名称：<input name="itemsCustom.name" value="查询">
            </td>
            <td><input type="button" value="查询" onclick="queryItems()"></td>
            <td><input type="button" value="批量修改提交" onclick="editItemsAllSubmit()"></td>
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
        <c:forEach items="${itemsList}" var="item" varStatus="status">
             <tr>
                <td><input name="itemsCustomList[${status.index}].name" value="${item.name}"/> </td>
                <td><input name="itemsCustomList[${status.index}].price" value="${item.price}"/> </td>
                <td><input name="itemsCustomList[${status.index}].detail" value="${item.detail}"/> </td>
                <td><input name="itemsCustomList[${status.index}].createtime" value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> </td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>
