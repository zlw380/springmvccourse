
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>提交参数给Controller</h2>

    <br/>
    <form action="receiveproperty.do" method="get">
        姓名:<input type="text" name="name"><br/>
        年龄:<input type="text" name="age"><br/>
        <input type="submit" value="get方法提交参数">
    </form>
    <form action="receiveproperty.do" method="post">
        姓名:<input type="text" name="name"><br/>
        年龄:<input type="text" name="age"><br/>
        <input type="submit" value="post方法提交参数">
    </form>
    <br/>
    <p>请求参数名和处理器方法的形参名不一样</p>
    <form action="receiveparam.do" method="post">
        姓名:<input type="text" name="rname"><br/>
        年龄:<input type="text" name="rage"><br/>
        <input type="submit" value="post方法提交参数">
    </form>
    <br/>
    <p>使用java对象接收请求参数</p>
    <form action="receiveobject.do" method="post">
        姓名:<input type="text" name="name"><br/>
        年龄:<input type="text" name="age"><br/>
        <input type="submit" value="post方法提交参数">
    </form>
    <br/>

<%--    <form action="user/first.do" method="post">
        <input type="text" name="name">
        <input type="submit" value="post请求first.do">
    </form>--%>

<%--前面再加个"/"就是绝对路径的请求，地址就不带module名了，会报404错误
    <p><a href="/test/first.do">发起other.do的请求</a> </p>--%>
</body>
</html>
