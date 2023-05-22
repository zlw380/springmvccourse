package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * @Controller:创建控制器对象，对象放在springmvc容器中
 * 位置：在类的上面
 * 和spring中的@Service、@Component一样
 * 能处理请求的都是控制器(处理器)：
 * MyController能处理请求，叫做后端控制器(back controller)
 */

@Controller
public class MyController {
    /*
    * 处理用户提交的请求，springmvc是使用方法来处理的
    * 方法是自定义的，可以有多种返回值，多种参数，方法名称自定义*/

/*    准备使用doSome方法处理some.do请求
        需要一个注解 @RequestMapping：请求映射，作用是把一个请求地址和一个方法绑定在一起
        一个请求指定一个方法处理
        属性：1.value 是一个String，表示请求的uri地址的(some.do)，value的值必须是唯一的，不能重复
             在使用时，推荐地址以"/"为开头
        位置：1.在方法的上面，常用的。
             2.在类的上面
        说明：使用@RequestMapping修饰的方法叫做处理器方法或者控制器方法
             使用@RequestMapping修饰的方法可以处理请求的，类似Servlet中的doGet，doPost

         返回值：ModelAndView 表示本次请求的处理结果
         Model：数据，请求处理完成后，要显示给用户的数据
         View：视图，比如jsp等等
    */

    @RequestMapping(value = {"/some.do","/first.do"})
    public ModelAndView doSome(){   //doGet()
        //处理some.do请求，相当于service调用处理完成了
        ModelAndView mv =  new ModelAndView();
/*        添加数据,框架在请求的最后把数据放入到request作用域。
        相当于执行request.setAttribute("msg","value-欢迎欢迎热烈欢迎");
        这里可以将ModelAndView对象看作HttpServletRequest对象(以下简称request对象)
        将request对象作为域对象使用，将service层返回的结果通过request.setAttribute方法
        传入request对象的Map集合当中，并由request对象携带通过请求转发
        (request.getRequestDispatcher("地址").forward(request,response))
        将这些数据带到另外一个资源(如jsp页面)当中
        */
        mv.addObject("msg","value-欢迎欢迎热烈欢迎!");
        mv.addObject("fun","value-执行的是doSome方法");

        //指定视图，指定视图的完整路径
        //框架对视图执行的forward操作，相当于
        //request.getRequestDispatcher("/show.jsp").forward(request,response)
        //请求转发

        //mv.setViewName("/show.jsp");
        //mv.setViewName("/WEB-INF/view/show.jsp");
        //当配置了视图解析器后，可以使用逻辑名称(文件名)，指定视图
        //框架会使用视图解析器的前缀+逻辑名称+后缀 组成完整路径，这里就是字符串拼接操作
        mv.setViewName("show");
        //只需要把结果/数据和视图放入mv，后面setAttribute、请求转发的步骤由框架来完成
        //返回mv
        return mv;
    }

    @RequestMapping(value = {"/other.do","/second.do"})
    public ModelAndView doOther(){

        ModelAndView mv =  new ModelAndView();

        mv.addObject("msg","value-再次欢迎!");
        mv.addObject("fun","value-执行的是doOther方法");

        mv.setViewName("other");

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
   获取到对象引用out之后就可以通过引用对response中作为属性的PrintWriter对象进行操作
   后面out.write()是向PrintWriter流中装载注入数据并使用PrintWriter流向客户端输出字符
   还有out.print()是字节输出流的方法
	这其实都是在直接操作response内的属性，最后传给浏览器的都是response中的信息
	如果你new一个PrintWriter，再操作out那就跟response一点关系都没，response里没获得任何数据
   */
