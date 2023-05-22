
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>/WEB-INF/view/hello.jsp</h3><br/>
    <h3>Key-msg数据:${param.myname}</h3><br/>
    <h3>Key-fun数据:${param.myage}</h3>
    <h3>取参数数据：<%=request.getParameter("myname")%></h3>
<%--param是参数集合
    Key-msg数据:爱丽丝
    Key-fun数据:17
    取参数数据：爱丽丝
    需要从新的request对象取得通过get请求传递的参数--%>
</body>
</html>
