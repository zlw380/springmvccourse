package org.example.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器类，拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {

    //验证登录的用户信息，正确return true，其他return false
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("11111111interceptor MyInterceptor's preHandle()");
        String loginName = "";
        //从session中获取name的值
        Object attr = request.getSession().getAttribute("name");
        if(attr != null){
            loginName = (String) attr;
        }
        //判断登录的账户，是否符合要求
        if(!"zs".equals(loginName)){
            //若对应的session对象中name值不是zs，请求被拦截，给用户提示
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return false;
        }
        //session会话对象中保存的name值是zs，可以正常处理请求
        return true;
    }

}

/*请求->过滤器->servlet->拦截器->controller
* 过滤器是配置文件事写在web.xml给tomcat管理的，拦截器是springmvc管理的*/
