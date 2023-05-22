
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>"/>
<%--    <base href="http://localhost:8080/ch06_path/"/>--%>
</head>
<body>
    <p>第一个springmvc的项目</p>
    <p><a href="user/some.do">发起user/some.do请求</a> </p>
    <br/>

<%--请求前不加斜杠，请求地址为
    http://localhost:8080/ch06_path/user/some.do
    请求前加斜杠，请求地址为
    http://localhost:8080/user/some.do
    前面再加个"/"，参考地址就变为你的服务器地址：http://localhost:8080/
    使用EL表达式可以解决这个问题:
    <p><a href="${pageContext.request.contextPath}/user/some.do">发起user/some.do请求</a> </p>
    --%>
</body>
</html>
