package org.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller

public class MyController {

    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name,Integer age){

        ModelAndView mv =  new ModelAndView();
        System.out.println("execute myController doSome method");

        mv.addObject("myname",name);
        mv.addObject("myage",age);

        mv.setViewName("show");
        return mv;
    }

}
