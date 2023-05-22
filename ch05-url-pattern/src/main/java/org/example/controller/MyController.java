package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping(value = "/some")
    public ModelAndView doSome(Student myStudent) {
        System.out.println("doSome,name=" + myStudent.getName() + " age=" + myStudent.getAge());
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName",myStudent.getName());
        mv.addObject("myAge",myStudent.getAge());
        mv.addObject("myStudent",myStudent);
        mv.setViewName("show");
        return mv;
    }

}

