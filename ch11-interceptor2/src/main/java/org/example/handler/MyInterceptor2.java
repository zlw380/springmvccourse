package org.example.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器类，拦截用户的请求
public class MyInterceptor2 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("22222222interceptor MyInterceptor's preHandle()");
        //return true;
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {
        System.out.println("22222222interceptor MyInterceptor's postHandle()");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("22222222interceptor MyInterceptor's afterCompletion()");

    }
}

/*  多个过滤器执行顺序：
    11111111interceptor MyInterceptor's preHandle()
    22222222interceptor MyInterceptor's preHandle()
    execute myController doSome method
    22222222interceptor MyInterceptor's postHandle()
    11111111interceptor MyInterceptor's postHandle()
    22222222interceptor MyInterceptor's afterCompletion()
    11111111interceptor MyInterceptor's afterCompletion()

    拦截器1为true，拦截器2为false：
    11111111interceptor MyInterceptor's preHandle()
    22222222interceptor MyInterceptor's preHandle()
    11111111interceptor MyInterceptor's afterCompletion()
    当前拦截器的preHandler方法返回true，那么他的afterCompletion方法一定会执行

    拦截器1为false，拦截器2为true/false：
    11111111interceptor MyInterceptor's preHandle()

    （1）浏览器提交请求到中央调度器
    （2）中央调度器直接将请求转给处理器映射器。
    （3）处理器映射器会根据请求，找到处理该请求的处理器，并将其封装为处理器执行链后
    返回给中央调度器。
    （4）中央调度器根据处理器执行链中的处理器，找到能够执行该处理器的处理器适配器。
    （5）处理器适配器调用执行处理器。
    （6）处理器将处理结果及要跳转的视图封装到一个对象 ModelAndView 中，并将其返回给
    处理器适配器。
    （7）处理器适配器直接将结果返回给中央调度器。
    （8）中央调度器调用视图解析器，将 ModelAndView 中的视图名称封装为视图对象。
    （9）视图解析器将封装了的视图对象返回给中央调度器

    （10）中央调度器调用视图对象(即jsp页面对象，即servlet对象)，

         接下来框架会将数据放入request作用域中并执行请求转发操作，此时视为请求处理已完成
         紧接着执行拦截器的afterCompletion()方法
         ============================================================================
         此时请求已经转发到新的资源当中，将请求转发到的jsp文件编译为servlet
         并接收request作用域中的数据对页面进行渲染，形成静态页面后发送给浏览器

          让其自己进行渲染，即进行数据填充，形成响应对象。

    （11）中央调度器响应浏览器。

    请求->过滤器->servlet->拦截器->controller
*   过滤器是配置文件事写在web.xml给tomcat管理的，拦截器是springmvc管理的*/
