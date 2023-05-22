
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
</head>
<body>
    <h2>提交参数给Controller</h2>

    <br/>
    <p>处理器方法返回ModelAndView</p>
    <form action="some" method="post">
        姓名:<input type="text" name="name"><br/>
        年龄:<input type="text" name="age"><br/>
        <input type="submit" value="post方法提交参数">
    </form>
    <br/>
    <img src="image/shiinano1.png" alt="我是一个静态资源，不能显示" style="height: 240px;width: 120px">
<%--    <img src="static/image/shiinano1.png" style="height: 240px;width: 120px">--%>


<%--    <form action="user/first.do" method="post">
        <input type="text" name="name">
        <input type="submit" value="post请求first.do">
    </form>--%>

<%--前面再加个"/"就是绝对路径的请求，地址就不带module名了，会报404错误
    <p><a href="/test/first.do">发起other.do的请求</a> </p>--%>
</body>
</html>
