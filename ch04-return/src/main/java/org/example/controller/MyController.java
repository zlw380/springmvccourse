package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*@RequestMapping:
 * value:所有请求地址的公共部分，叫做模块名称
 * 位置:放在类的上面*/

@Controller

public class MyController {

    /*处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
    */
    @RequestMapping(value = "/returnStringview.do")
    public String doReturnView(HttpServletRequest request,Student myStudent){
        System.out.println("doReturnView,name="+myStudent.getName()+" age="+myStudent.getAge());
        //可以自己手工添加数据
        request.setAttribute("myname",myStudent.getName());
        request.setAttribute("myage",myStudent.getAge());
        request.setAttribute("mystudent",myStudent);
        //show是逻辑视图名称，项目中配置了视图解析器
        //框架对视图执行forward转发操作
        return "show";
    }

    //处理器方法返回String，表示完整视图路径，此时不能配置视图解析器
    @RequestMapping(value = "/returnStringview2.do")
    public String doReturnView2(HttpServletRequest request,Student myStudent){
        System.out.println("doReturnView2,name="+myStudent.getName()+" age="+myStudent.getAge());
        //可以自己手工添加数据
        request.setAttribute("myname",myStudent.getName());
        request.setAttribute("myage",myStudent.getAge());
        request.setAttribute("mystudent",myStudent);
        //完整视图路径，项目中不能配置视图解析器
        //框架对视图执行forward转发操作
        //消息 文.件[/WEB-INF/view/WEB-INF/view/show.jsp.jsp] 未找到
        return "/WEB-INF/view/show.jsp";
    }

    //处理器方法返回void，响应ajax请求
    //手工实现ajax，json数据：代码有重复的,可以利用框架简化
    //1.java对象转为json格式数据 2.通过HttpServletResponse输出json数据
    @RequestMapping(value = "/returnVoid-ajax.do")
    public void doReturnAjax(HttpServletResponse response,String name,Integer age) throws IOException {
        System.out.println("doReturnAjax,name="+name+" age="+age);
        //处理ajax，使用json做数据的格式
        //service调用完成了，使用Student表示处理结果
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        System.out.println(student);

        String json = "";
        //把结果的对象转为json格式的数据
        if(student != null) {
            ObjectMapper om = new ObjectMapper();
            //转换结果赋给json字符串
            json = om.writeValueAsString(student);
            System.out.println("student change json=="+json);
        }

        //输出数据，响应ajax请求
        //设置响应内容类型，告诉浏览器，我是json格式的数据
        response.setContentType("application/json;charset=utf-8");
        //获取response的输出流对象
        PrintWriter pw = response.getWriter();
        //向输出流对象注入数据，是json格式的字符串，并通过流向浏览器输出数据
        pw.println(json);
        //刷新缓存
        pw.flush();
        //关闭输出流
        pw.close();

    }

    /*处理器方法返回一个Student，通过框架转为json，响应ajax请求
    * @ResponseBody:
    *   作用：把处理器方法返回对象转为json格式数据后，通过HttpServletResponse输出给浏览器
    *   位置：在方法的定义上面，和其他注解没有顺序的先后
    * 返回对象框架的处理流程：
    * 1.框架会把返回的Student类型对象，调用框架中的ArrayList<HttpMessageConverter>
    *   存放HttpMessageConverter接口的实现类对象的数组集合(ArrayList)中(List集合中以数组的形式存放对象)
    *   每个消息转换器实现类的canWrite()方法，来检查哪个HttpMessageConverter接口的实现类能处理
    *   Student类型的数据--MappingJackson2HttpMessageConverter的canWrite方法会返回true
    *
    * 2.框架会调用该实现类的write()方法，MappingJackson2HttpMessageConverter的write()方法
    *   把student对象转为json格式的字符串，调用Jackson的ObjectMapper实现转为json
    *   Content-Type: application/json;charset=utf-8
    *
    * 3.框架会调用@ResponseBody的功能 把2的结果数据输出到浏览器，ajax请求处理完成*/
    @RequestMapping(value = "/returnStudentJson.do")
    @ResponseBody
    public Student doStudentJsonObject(String name,Integer age) throws IOException {
        //调用service，获取请求结果数据，Student对象表示结果数据
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        return student;
        //会被框架自动转为json格式字符串
        //消息转换器接口中的write方法会被
        //MappingJackson2HttpMessageConverter实现类继承并调用
        //write方法会将处理器方法返回的java对象
        //调用jackson中的ObjectMapper转为json格式的字符串

        //给框架返回的是Student对象，剩下的格式转换和注入response响应对象都由框架完成

    }

    /*处理器方法返回List<Student>，Student类型对象的集合
    * 步骤与返回Student对象基本相同
    * 只不过框架接收的是List<Student>(其实就是对象数组)，再将其转换为json字符串数组(json array)*/
    @RequestMapping(value = "/returnStudentJsonArray.do")
    @ResponseBody
    public List<Student> doStudentJsonObjectArray(String name, Integer age) throws IOException {

        //new一个存放Student类型对象的List集合对象
        //这个集合对象存放在堆区，其引用list给到方法栈帧当中
        List<Student> list = new ArrayList<>();

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        list.add(student);

        student = new Student();
        student.setName("alice");
        student.setAge(22);
        //添加了数个对象到集合当中，它们的地址是相邻的
        //以数组的形式存放在集合中，可以实现随机访问
        list.add(student);

        return list;

        //直接发送请求，get方法，不从前端发送ajax请求：
        //http://localhost:8080/ch04_return/returnStudentJsonArray.do?name=marisa&age=20
        //返回浏览器,json字符串数组，没有视图没有jquery转json对象：
        //[{"name":"marisa","age":20},{"name":"alice","age":22}]
    }

    /*处理器方法返回的是String，String表示数据的，不是视图
    * 区分返回值字符串是数据还是视图，就看有没有@ResponseBody这个注解
    * 如果有@ResponseBody，返回String就是数据，反之就是视图
    *
    * 默认使用"text/plain;charset=ISO-8859-1"作为contentType，导致中文有乱码
    * 解决方案：给RequestMapping增加一个属性produces，使用这个属性指定新的contentType
    *
    * 返回对象框架的处理流程：
    *  1.框架会把返回的String类型，调用框架中的ArrayList<HttpMessageConverter>中每个类的canWrite()方法
    *    检查哪个HttpMessageConverter接口的实现类能处理String类型的数据--StringHttpMessageConverter
    *
    *  2.框架会调用实现类的write（）， StringHttpMessageConverter的write()方法
    *    把字符按照指定的编码处理 text/plain;charset=ISO-8859-1
    *
    *  3.框架会调用@ResponseBody把2的结果数据输出到浏览器， ajax请求处理完成*/
    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=utf-8")
    @ResponseBody //这个东西输出不走过滤器
    public String doStringData(String name,Integer age){
        //猜测：返回的是一个普通的字符串，简单数据类型，所以不会被框架自动转json格式(依然会被框架处理编码)
        //先看dataType：json，前端没设置dataType就看StringHttpMessageConverter：text/plain;charset=ISO-8859-1
        //前端ajax请求不指定dataType时
        //response响应头会自动配置响应内容类型为：Content-Type: text/plain;charset=ISO-8859-1
        //浏览器接收后会根据响应体字符编码，自动调整其对响应体内容的解码方式
        //即会使用响应体的字符编码显示响应体内容
        //因此浏览器接收到文本之后会使用ISO-8859-1字符集对文本进行解码，产生乱码
        return "Hello SpringMVC 返回对象，表示数据";
    }
}

//返回值是String 会使用StringHttpMessageConverter这个类的输出,这个类的对象默认使用格式ISO-8859-1来输出，所以前面你在过滤器设置response的写入格式会被覆盖
//这样看来String的数据返回值也会被注解驱动创建的消息转换器实现类对象接收并处理，String本身也是对象
//前面设置的过滤器给强制请求对象(HttpServletRequest)和强制应答对象(HttpServletResponse)都设置了新的编码方式utf-8 前面不懂的别乱说

/*  脑补的消息转换器实现代码：
*   List<HttpMessageConverter> list  = new ArrayList<>();
*   HttpMessageConverter httpMessageConverter1 = new HttpMessageConverterImpl1();
*   HttpMessageConverter httpMessageConverter2 = new HttpMessageConverterImpl2();
*   HttpMessageConverter httpMessageConverter3 = new HttpMessageConverterImpl3();
*   list.add(httpMessageConverter1);
*   list.add(httpMessageConverter2);
*   list.add(httpMessageConverter3);
*   boolean canWrite1 = list[0].canWrite();
*   boolean canWrite2 = list[1].canWrite();
*   boolean canWrite3 = list[2].canWrite();
*   if(canWrite 1 == true){
*       list[0].Write();
*   }else if(canWrite 2 == true){
*       list[1].Write();
*   }else if(canWrite 3 == true){
*       list[2].Write();
*   }
*
*   1、text/html的意思是将文件的content-type设置为text/html的形式，浏览器在获取到这种文件时会自动调用html的解析器对文件进行相应的处理。

    2、text/plain的意思是将文件设置为纯文本的形式，浏览器在获取到这种文件时并不会对其进行处理。*/

