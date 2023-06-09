package org.example.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器类，拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {

    private long btime = 0;
    /*preHandle方法，叫做预处理方法
    * 这个方法很重要，是整个项目的入口，门户
    * 当preHandler返回true，请求可以被处理
    * 当preHandler返回false，请求到此方法就截止
    * 参数：
    * Object handler：被拦截的控制器对象
    * 返回值boolean
    * true：请求是通过了拦截器的验证，可以执行处理器方法
    *       interceptor MyInterceptor's preHandle()
    *       execute myController doSome method
    *       interceptor MyInterceptor's postHandle()
    *       interceptor MyInterceptor's afterCompletion()
    * false：请求没有通过拦截器的验证，请求到达拦截器就截止了，请求没有被处理
    *       interceptor MyInterceptor's preHandle()
    *
    * 特点：1.方法在控制器方法（MyController的doSome）之前先执行的
    *       用户的请求首先到达此方法
    *      2.在这个方法中可以获取请求的信息，验证请求是否符合要求
    *       可以验证用户是否登录，验证用户是否有权限去访问某个链接地址（url）
    *       如果验证失败，可以截断请求，这个请求不能被处理
    *       如果验证成功，可以放行请求，此时控制器方法才能执行
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        btime = System.currentTimeMillis();
        System.out.println("interceptor MyInterceptor's preHandle()");
        //计算的业务逻辑，根据计算结果，返回true或者false
        //若返回假，可以给浏览器一个返回结果,给用户反馈
        //request.getRequestDispatcher("/tips.jsp").forward(request,response);
        //return false;
        //返回值真是验证通过
        return true;
    }

    /*postHandle：后处理方法
    * 参数：
    * Object handler：被拦截的处理器对象MyController
    * ModelAndView mv：处理器方法的返回值
    * 特点：
    * 1.在处理器方法之后执行的（MyController.doSome()）
    * 2.能够获取到处理器方法的返回值ModelAndView，可以修改ModelAndView中的
    * 数据和视图，可以影响到最后的执行结果
    * 3.主要是对原来的执行结果做二次修正*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {
        System.out.println("interceptor MyInterceptor's postHandle()");
        //对原来的doSome执行结果，需要调整
        if(mv != null){
            //添加数据
            mv.addObject("mydate",new Date());
            //修改视图
            mv.setViewName("other");
        }
    }

    /*afterCompletion：最后执行的方法
    * 参数：
    * Object handler：被拦截的处理器对象MyController
    * Exception ex：程序中发生的异常
    * 特点：
    * 1.在请求处理完成后执行的
    *   框架中规定是当你的视图处理完成后，对视图执行了forward
    *   就认为请求处理完成
    * 2.一般做资源回收工作的，程序请求过程中创建了一些对象
    *   在这里可以删除，把占用的内存回收*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("interceptor MyInterceptor's afterCompletion()");
        long etime = System.currentTimeMillis();
        System.out.println("calculate time from preHandle to afterCompletion:"+(etime-btime));
    }
}

/*请求->过滤器->servlet->拦截器->controller
* 过滤器是配置文件事写在web.xml给tomcat管理的，拦截器是springmvc管理的*/
