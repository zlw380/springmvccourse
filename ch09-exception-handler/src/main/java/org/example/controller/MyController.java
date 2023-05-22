package org.example.controller;

import org.example.exception.AgeException;
import org.example.exception.MyUserException;
import org.example.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller

public class MyController {

    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name,Integer age) throws MyUserException {

        ModelAndView mv =  new ModelAndView();
        //根据请求参数抛出异常
        //Exception e = new NameException();
        //e.printStackTrace();
        //但现在已不需要再加try、catch块，把异常抛出给框架

        if (!"zs".equals(name)){
            throw new NameException("姓名不正确!");
        }
        if (age == null || age > 80){
            throw new AgeException("年龄比较大!");
        }
        mv.addObject("myname",name);
        mv.addObject("myage",age);

        mv.setViewName("show");
        return mv;
    }

}
