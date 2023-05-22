package org.example.handler;

import org.example.exception.AgeException;
import org.example.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*  @ControllerAdvice：
    控制器通知/增强（也就是说给控制器类增加功能--异常处理功能）
*   位置：在类的上面
    特点：必须让框架知道这个注解所在的包名，需要在springmvc配置文件声明组件扫描器
    指定@ControllerAdvice所在的包名
    有些类似于AspectJ的Aspect类*/
@ControllerAdvice
public class GlobalExceptionHandler {
    //定义方法，处理发生的异常
    /*处理异常的方法和控制器方法的定义一样
    * 可以有多个参数，也可以有ModelAndView
    * String、void、Object等多个类型的返回值
    *
    * 形参：Exception，表示Controller类中抛出的异常对象
    * 通过形参可以获取发生的异常信息
    *
    * @ExceptionHandler(异常的class)：表示异常的类型
    * 当发生此类型异常时由当前方法处理*/

    //value的值是异常类对象数组
    @ExceptionHandler(value = NameException.class)
    public ModelAndView doNameException(Exception exception){
        //处理NameException的异常
        /*异常发生处理逻辑：
        * 1.需要把异常记录下来，记录到数据库，日志文件
        *   记录异常发生的时间，哪个方法发生的，异常的错误内容
        * 2.发送通知，把异常的信息通过邮件，短信，微信发送给相关人员
        * 3.给用户友好地提示*/
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","姓名必须是zs，其他用户不能访问");
        mv.addObject("ex",exception);
        mv.setViewName("nameError");
        return mv;
    }

    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception exception){
        //处理NameException的异常
        /*异常发生处理逻辑：
         * 1.需要把异常记录下来，记录到数据库，日志文件
         *   记录异常发生的时间，哪个方法发生的，异常的错误内容
         * 2.发送通知，把异常的信息通过邮件，短信，微信发送给相关人员
         * 3.给用户友好地提示*/
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","您的年龄不能大于80！");
        mv.addObject("ex",exception);
        mv.setViewName("ageError");
        return mv;
    }

    //处理其他异常，NameException，AgeException以外，不知类型的异常
    //当控制器方法抛出的异常与其他处理异常的方法匹配不上时，调用该方法处理异常
    //这种没有value的兜底方法，只能有一个，相当于分支语句的else
    @ExceptionHandler
    public ModelAndView doOtherException(Exception exception){
        //处理其他异常
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","兜底异常处理方法");
        mv.addObject("ex",exception);
        mv.setViewName("defaultError");
        return mv;
    }
}
