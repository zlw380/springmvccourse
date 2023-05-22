
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
            $("button").click(function (){
                //alert("button click");
                $.ajax({
                    //url:"returnVoid-ajax.do",
                    //url:"returnStudentJson.do",
                    //url:"returnStudentJsonArray.do",
                    url:"returnStringData.do",
                    data:{
                        name:"marisa",
                        age:20
                    },
                    type:"post",
                    //如果没有指定请求的是json格式的数据(Accept:*/*),但手动设置了响应内容的类型为json：application/json
                    //jquery也会根据响应对象的设置(response.setContentType)尝试将数据转为json对象

                    //配置后，response响应头：Content-Type: application/json;charset=utf-8
                    //       request请求头： Accept: application/json, text/javascript, */*; q=0.01

                    //dataType:"json",
                    //请求json格式的数据，告诉服务器我想要接收json,服务器框架就会通过@ResponseBody
                    //自动设置响应内容类型为json:application/json
                    //使浏览器判断接收的数据类型为json格式并调用jquery处理
                    //但是有时返回的数据实际上并没有被转为json格式(还是文本数据)，就会出错处理不了无法转为json对象

                    dataType:"text",

                    //resp是自定义形参，代表从服务器端返回的数据
                    //服务器给浏览器传回来的是json格式的字符串
                    //浏览器的jquery拿到结果之后，会把它转成一个json的Object对象使用
                    //resp就是这个json对象
                    success:function (resp){
                        //从服务器端返回的是json格式的字符串{"name":"zhangsan","age":"20"}
                        //jquery会把这个字符串转为json对象，赋值给resp形参
                        //alert(resp.name+"==="+resp.age+"==="+resp);

                        //[{"name":"marisa","age":20},{"name":"alice","age":22}]
                        //返回json字符串数组再被jquery转为json对象数组
                        //循环遍历json数组中的对象
                        //i是循环变量(数组下标)，n代表数组中的对象
                        /*$.each(resp,function (i,n){
                            alert(i+"   "+n.name+"   "+n.age)
                        })*/

                        alert("返回的是文本数据："+resp);
                    }
                })
            })
        })
    </script>
</head>
<body>
    <h2>提交参数给Controller</h2>

    <br/>
    <p>处理器方法返回String表示视图名称</p>
    <form action="returnStringview.do" method="post">
        姓名:<input type="text" name="name"><br/>
        年龄:<input type="text" name="age"><br/>
        <input type="submit" value="post方法提交参数">
    </form>
    <br/>
    <p>处理器方法返回String表示完整路径</p>
    <form action="returnStringview2.do" method="post">
        姓名:<input type="text" name="name"><br/>
        年龄:<input type="text" name="age"><br/>
        <input type="submit" value="post方法提交参数">
    </form>
    <br/>

    <button id="btn">发起ajax请求</button>

<%--    <form action="user/first.do" method="post">
        <input type="text" name="name">
        <input type="submit" value="post请求first.do">
    </form>--%>

<%--前面再加个"/"就是绝对路径的请求，地址就不带module名了，会报404错误
    <p><a href="/test/first.do">发起other.do的请求</a> </p>--%>
</body>
</html>
