package org.example.controller;

import org.example.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*@RequestMapping:
* value:所有请求地址的公共部分，叫做模块名称
* 位置:放在类的上面*/

@Controller

public class MyController {

    /*逐个接收请求参数
    * 要求： 处理/控制器方法的形参名和请求中参数名必须一致
    *       同名的请求参数赋值给同名的形参
    * 框架接收请求参数
    *       1.使用request对象接收请求参数
    *       String strName = request.getParameter("name");
    *       String strAge = request.getParameter("age");
    *       2.springmvc框架通过 DispatcherServlet中央调度器 调用 MyController控制器 的doSome()方法
    *       调用方法时，按名称对应，把接收的参数赋给形参
    *       doSome(strName,Integer.valueOf(strAge))
    *       框架会提供类型转换的功能，能把String转为 int long float double等类型
    *
    * 400状态码是客户端错误，表示提交请求参数过程中，发生了问题*/
    @RequestMapping(value = "/receiveproperty.do")
    public ModelAndView doFirst(String name,Integer age){
        System.out.println("doFirst,name="+name+" age="+age);
        //可以在方法中直接使用name，age
        ModelAndView mv =  new ModelAndView();

        mv.addObject("myname",name);
        mv.addObject("myage", age);

        mv.setViewName("show");

        return mv;
    }

    /*请求中参数名和处理器方法的形参名不一样
    * @RequestParam:解决请求中参数名和形参名不一样的问题
    *               属性:1.value 请求中的参数名称
    *                   2.required 是一个boolean类型，默认是true
    *                     true:表示请求中必须包含此参数
    *               位置:在处理器方法的形参定义的前面*/
    @RequestMapping(value = "/receiveparam.do")
    public ModelAndView receiveParam(@RequestParam(value = "rname",required = false) String name,
                                     @RequestParam(value = "rage",required = false) Integer age){
        System.out.println("doFirst,name="+name+" age="+age);

        ModelAndView mv =  new ModelAndView();

        mv.addObject("myname",name);
        mv.addObject("myage", age);

        mv.setViewName("show");

        return mv;
    }

    /*处理器方法形参是java对象，这个对象的属性名和请求中参数名一样的
    * 框架会创建形参的java对象，给属性赋值，请求中的参数是name，框架就会调用setName()
    * */
    @RequestMapping(value = "/receiveobject.do")
    public ModelAndView receiveObject(Student myStudent){
        System.out.println("receiveObject,name="+myStudent.getName()+" age="+myStudent.getAge());

        ModelAndView mv =  new ModelAndView();

        mv.addObject("myname",myStudent.getName());
        mv.addObject("myage", myStudent.getAge());
        mv.addObject("mystudent",myStudent);
        mv.setViewName("show");

        return mv;
    }
}

   /* request请求对象 response应答对象
      request.setCharacterEncoding(“UTF-8”)的作用是设置对客户端请求和数据库取值时的编码，
      不指定的话使用iso-8859-1(只解决POST乱码,解决GET乱码可以修改tomcat的server.xml中的URIEncoding属性)

      response.setCharacterEncoding(“UTF-8”)的作用是指定服务器响应给浏览器的编码

      response.setContentType(“text/html;charset=utf-8”)的作用是指定服务器响应给浏览器的数据类型和编码。

      而浏览器是根据页面中的<meta charset="UTF-8">这个标签来确定要用什么编码打开页面

      get方式的参数放在了请求头上面所以是由tomcat服务器在解析请求uri地址时来解码，tomcat解码的默认方式是utf-8(还是修改了一下配置文件)
      post方式的参数放在了请求体中，由接收请求时服务器创建的request对象来解码，解码方式默认为ISO-8859-1,解码中文会乱码

      request域对象是把数据带到请求转发的资源，servlet也好，jsp也好，html也好
      最后数据传给浏览器还是要先给到response对象的输出流里面
      在请求地址对应的servlet中可以直接传然后直接展示给用户
      在转发到的jsp页面下也可以使用java语言传（jsp页面本质上也是servlet）
   */
