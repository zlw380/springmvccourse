package org.example.controller;

import org.example.domain.Student;
import org.example.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    //用注解方式给引用类型赋值
    //@Resource注解默认byName自动注入，byName匹配不上就使用byType自动注入
    //byType自动注入支持自动查找并注入引用类型接口的实现类或子类对象引用完成属性赋值
    //springmvc容器与spring容器类似于子类与父类的关系
    //因此springmvc容器可以继承spring容器中声明的对象
    //这里就使用了spring容器中service对象的引用作为属性
    //给springmvc容器中的StudentController对象的属性赋值
    @Resource
    private StudentService service;

    @RequestMapping(value = "/addStudent.do")
    public ModelAndView addStudent(Student student){
        ModelAndView mv = new ModelAndView();
        String tips = "注册失败";
        //调用service处理student
        int nums = service.addStudent(student);
        if(nums > 0){
            //注册成功
            tips = "学生【"+student.getName()+"】注册成功";
        }
        //添加数据
        mv.addObject("tips",tips);
        //指定结果页面
        mv.setViewName("result");
        return mv;
    }

    //处理查询，响应ajax
    @RequestMapping("/queryStudent.do")
    @ResponseBody
    public List<Student> queryStudent(){
        //参数检查，简单的数据处理
        List<Student> students = service.findStudents();
        return students;
        //直接提交请求结果：
        //[{"id":1,"name":null,"age":null},{"id":2,"name":"marisa","age":17},{"id":3,"name":"marisa","age":22}]
        //浏览器接收到json格式字符串数组
    }
}
