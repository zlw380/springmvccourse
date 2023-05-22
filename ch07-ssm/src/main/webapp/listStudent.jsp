
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>查询学生，使用ajax</title>
    <base href="<%=basePath%>"/>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        //$(function(){})是在页面加载之后执行的
        $(function (){
            //在当前页面dom对象加载后，执行loadStudentData()
            //页面一成功加载，就主动发起ajax请求获取数据
            loadStudentData();
            $("#btnLoader").click(function (){
                loadStudentData();
                /*$.ajax({
                    url:"student/queryStudent.do",
                    type:"get",
                    dataType:"json",
                    success:function (data){
                        //清除旧的数据
                        $("#info").html("");
                        //添加新的数据
                        $.each(data,function (i,n){
                            $("#info").append("<tr>")
                            .append("<td>"+n.id+"</td>")
                            .append("<td>"+n.name+"</td>")
                            .append("<td>"+n.age+"</td>")
                            .append("</tr>")
                        })

                        //alert("data="+data);
                        //data=[object Object],[object Object],[object Object] json对象数组
                    }
                })*/
            })
        })

        function loadStudentData(){
            $.ajax({
                url:"student/queryStudent.do",
                type:"get",
                dataType:"json",
                success:function (data){
                    //清除旧的数据
                    $("#info").html("");
                    //添加新的数据
                    $.each(data,function (i,n){
                        $("#info").append("<tr>")
                            .append("<td>"+n.id+"</td>")
                            .append("<td>"+n.name+"</td>")
                            .append("<td>"+n.age+"</td>")
                            .append("</tr>")
                    })

                    //alert("data="+data);
                    //data=[object Object],[object Object],[object Object] json对象数组
                }
            })
        }
    </script>
</head>
<body>
    <div align="center">
        <table>
            <thead>
                <tr>
                    <td>学号</td>
                    <td>姓名</td>
                    <td>年龄</td>
                </tr>
            </thead>
            <tbody id="info">
            </tbody>
        </table>
        <input type="button" id="btnLoader" value="查询数据">
    </div>
</body>
</html>
