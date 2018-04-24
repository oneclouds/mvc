<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/10
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json交互测试</title>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">

        /*
        ajax中的data参数详解：
         要求为Object或String类型的参数，发送到服务器的数据。如果已经不是字符串，将自动转换为字符串格式。get请求中将附加在url后。
         防止这种自动转换，可以查看processData选项。对象必须为key/value格式.例如:{foo1:"bar1",foo2:"bar2"}转换为&foo1=bar1&foo2=bar2。如果是数组，JQuery将自动为不同值对应同一个名称。
         例如{foo:["bar1","bar2"]}转换为&foo=bar1&foo=bar2。

         */


        <%--请求json，输出json--%>
        function requestJson() {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/requestJson.action",
                contentType: "application/json;charset=utf-8",
                data: '{"name":"手机","price"：888}',
                dataType: "json",
                success: function (data) {
                    alert(data);
                }
            });
        }

        /*请求key/value,输出json*/
        function responseJson() {

            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/responseJson.action",
//                请求时key/value，这里不需要指定contentType，因为默认就是key/value类型：application/x-www-form-urlencoded
//                数据格式是json串，商品信息
                data: "name=小明&price=99",
                dataType: "json",
                success: function (data) {
                    alert(data.name);
                }

            });


        }

    </script>
</head>
<body>
<<input type="button" value="请求为json" onclick="requestJson()">
<<input type="button" value="请求为key/value" onclick="responseJson()">
</body>
</html>

