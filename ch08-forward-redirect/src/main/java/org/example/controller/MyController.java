package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*@RequestMapping:
* value:所有请求地址的公共部分，叫做模块名称
* 位置:放在类的上面*/

@Controller

public class MyController {

    /*@RequestMapping:请求映射
    * 属性:method，表示请求的方式，它的值是RequestMethod类的枚举值
    *     例如表示get请求方式，RequestMethod.GET
    *            post请求方式，RequestMethod.POST
    * 前端页面请求方式和注解里的值对不上，会报405错误*/

    //指定some.do使用get请求方式
    @RequestMapping(value = "/user/some.do")
    public ModelAndView doSome(){

        ModelAndView mv =  new ModelAndView();

        mv.addObject("msg","value-欢迎欢迎热烈欢迎!");
        mv.addObject("fun","value-执行的是doSome方法");

        mv.setViewName("/index.jsp");

        return mv;
    }

    /*处理器方法返回ModelAndView，实现转发forward
    * 语法：setViewName("forward:视图文件完整路径")
    * forward特点：不和视图解析器一同使用，就当项目中没有视图解析器*/
    @RequestMapping(value = "/doForward.do")
    public ModelAndView doForward(){

        ModelAndView mv =  new ModelAndView();

        mv.addObject("msg","value-doForward欢迎欢迎热烈欢迎!");
        mv.addObject("fun","value-执行的是doForward方法");
        //显式转发
        //mv.setViewName("forward:/WEB-INF/view/show.jsp");
        //不受视图解析器限制
        mv.setViewName("forward:/hello.jsp");
        return mv;
    }
    /*处理器方法返回ModelAndView，实现重定向redirect
    * 语法：setViewName("redirect:视图文件完整路径")
    * redirect特点：不和视图解析器一同使用，就当项目中没有视图解析器
    *
    * 框架对重定向的操作：
    * 1.框架会把Model中的简单类型数据，转为String使用，作为hello.jsp的get请求参数使用
    *   目的是在doRedirect.do 和 hello.jsp 这两次请求之间可以传递数据
    * 2.在目标hello.jsp页面可以使用参数集合对象${param}获取请求参数值
    *   ${param.myname}
    * 3.重定向不能访问/WEB-INF下受保护的资源*/
    @RequestMapping(value = "/doRedirect.do")
    public ModelAndView doRedirect(String name,Integer age){

        ModelAndView mv =  new ModelAndView();
        //数据放入到request作用域
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        //重定向
        mv.setViewName("redirect:/hello.jsp");
        //http://localhost:8080/ch08_forward/hello.jsp?myname=爱丽丝&myage=17

        //这样做是不行的，重定向不能访问/WEB-INF下受保护的资源
        //mv.setViewName("redirect:/WEB-INF/view/show.jsp");
        return mv;
    }

}

   /* 关于PrintWriter out = response.getWriter();的一些想法与猜测：
   getWriter() 是HttpServletResponse接口(以下当做并简称Response类，继承了ServletResponse接口)中(继承) 的一个方法
   它的作用是返回一个能够发送字符文本给客户端的PrintWriter类型的对象out
   因此Response类中一定有(继承了)一个PrintWriter类型的属性，这是一个引用数据类型，给它赋的值是一个PrintWriter对象的引用
    response对象内：
    private PrintWriter out;
   而这个PrintWriter类的对象会在创建response对象的同时被创建，然后其引用被赋给Response类中的PrintWriter类型的属性
    response对象外：
    PrintWriter out  = new PrintWriter(若有参数应该与response对象有关,比如要装入response对象的service层返回的数据？);
    response对象内：
    public void setWriter(PrintWriter out){this.out = out;}
    public PrintWriter getWriter(){return out;}
   再在Servlet中调用这个get方法getWriter()获取这个printWriter输出流对象

   与response对象一同创建出的PrintWriter类输出流对象out与response对象是存在联系的，且作为response对象的属性
   Servlet中的这个PrintWriter类输出流对象out只能从对应的response对象获取

   PrintWriter类对象在被创建后，作为response对象的一个属性，当中包含了response对象的一部分数据？
   out对象需要拿到对应的response对象中的流，即从这个response对象获取输出流
   如果直接new出一个printWriter对象就拿不到属于response对象的输出流

   PrintWriter out = response.getWriter();
   getWriter()返回一个PrintWriter object
   直接new的，那你再用流输出就不是输出到返回信息（response）了。
   那你输出也就没意义了。response是有基于流的概念，你从response拿到一个流，然后输出信息。

   不是所有的对象都要用new 的，有的类的对象是new不了的，
   这里的out对象是 从web容器中获取到的，而不是你新创建的，
   web容器加载servlet的时候已经为您创建好了out对象，你只要获取到然后使用就可以了！

   在获取PrintWriter输出流之前首先使用
   "response.setCharacterEncoding(charset)"
   设置字符以什么样的编码输出到浏览器，
   如：response.setCharacterEncoding("UTF-8");
   设置将字符以"UTF-8"编码输出到客户端浏览器，
   然后再使用response.getWriter();
   获取PrintWriter输出流，这两个步骤不能颠倒

   使用PrintWriter out = response.getWriter();获取PrintWriter输出流
   后面out.write()是使用PrintWriter流向客户端输出字符
   还有out.print()是字节输出流的方法
   */
