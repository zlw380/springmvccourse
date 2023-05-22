package org.example.vo;

//保存请求参数值的一个普通类
public class Student {
    //属性名和请求中参数名一样
    private String name;
    private Integer age;

    public Student() {
        System.out.println("Student non param constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName"+name);
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("setAge"+age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
