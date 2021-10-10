package com.jianzh5.blog.util;

import com.jianzh5.blog.orika.*;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jam
 * @date 2021/9/2 2:53 下午
 */
public class OrikaUtilsTest {


    private OrikaUtils orikaUtils;

    /**
     * 只拷贝相同的属性
     */
    @Test
    public void convertObject(){
        Student student = new Student("1","javadaily","jianzh5@163.com");
        Teacher teacher = OrikaUtils.convert(student, Teacher.class);
        System.out.println(teacher);
    }


    /**
     * 拷贝不同属性
     */
    @Test
    public void convertRefObject(){
        Student student = new Student("1","javadaily","jianzh5@163.com");

        Map<String,String> refMap = new HashMap<>(1);
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("email","emailAddress");
        Teacher teacher = OrikaUtils.convert(student, Teacher.class, refMap);
        System.out.println(teacher);

        Teacher convert = OrikaUtils2.INSTANCE.convert(student, Teacher.class, refMap);
        System.out.println("==="+convert);
    }

    /**
     * 只拷贝相同的属性集合
     */
    @Test
    public void convertList(){
        Student student1 = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("2","JAVA日知录","jianzh5@xxx.com");
        List<Student> studentList = Lists.newArrayList(student1,student2);

        List<Teacher> teacherList = OrikaUtils.convertList(studentList, Teacher.class);

        System.out.println(teacherList);
    }

    /**
     * 映射不同属性的集合
     */
    @Test
    public void convertRefList(){
        Student student1 = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("2","JAVA日知录","jianzh5@xxx.com");
        List<Student> studentList = Lists.newArrayList(student1,student2);

        Map<String,String> refMap = new HashMap<>(10);
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("email","emailAddress");

        List<Teacher> teacherList = OrikaUtils.convertList(studentList, Teacher.class,refMap);
        System.out.println(teacherList);
        List<Teacher> teacherList1 = OrikaUtils2.INSTANCE.convertList(studentList, Teacher.class, refMap);
        System.out.println("111:"+ teacherList1);
    }

    /**
     * 一对多映射
     */
    @Test
    public void convertComplexList1(){
        Student student1 = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("2","JAVA日知录","jianzh5@xxx.com");
        List<Student> studentList = Lists.newArrayList(student1,student2);

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudentGradeName("硕士");
        studentGrade.setStudentList(studentList);

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapperFactory.classMap(Student.class,Teacher.class)
                .field("email","emailAddress")
                .byDefault()
                .register();

        mapperFactory.classMap(StudentGrade.class, TeacherGrade.class)
        .field("studentGradeName", "teacherGradeName")
        .field("studentList", "teacherList")
        .byDefault()
        .register();

        TeacherGrade teacherGrade = mapper.map(studentGrade, TeacherGrade.class);


        System.out.println(teacherGrade);
    }


    /**
     * 一对多映射
     */
    @Test
    public void convertComplexList2(){
        Student student1 = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("2","JAVA日知录","jianzh5@xxx.com");
        List<Student> studentList = Lists.newArrayList(student1,student2);

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudentGradeName("硕士");
        studentGrade.setStudentList(studentList);

        Map<String,String> refMap1 = new HashMap<>(1);
        //map key 放置 源属性，value 放置 目标属性
        refMap1.put("email","emailAddress");
        OrikaUtils.register(Student.class,Teacher.class,refMap1);


        Map<String,String> refMap2 = new HashMap<>(2);
        //map key 放置 源属性，value 放置 目标属性
        refMap2.put("studentGradeName", "teacherGradeName");
        refMap2.put("studentList", "teacherList");


        TeacherGrade teacherGrade = OrikaUtils.convert(studentGrade,TeacherGrade.class,refMap2);
        System.out.println(teacherGrade);


//        Map<String,String> refMap3 = new HashMap<>(1);
//        //map key 放置 源属性，value 放置 目标属性
//        refMap3.put("email","emailAddress");
//        OrikaUtils2.INSTANCE.register(Student.class,Teacher.class,refMap3);
//
//
//        Map<String,String> refMap4 = new HashMap<>(2);
//        //map key 放置 源属性，value 放置 目标属性
//        refMap4.put("studentGradeName", "teacherGradeName");
//        refMap4.put("studentList", "teacherList");
//
//
//        TeacherGrade teacherGrade2 = OrikaUtils2.INSTANCE.convert(studentGrade,TeacherGrade.class,refMap4);
//        System.out.println("instance:" + teacherGrade2);



    }


    /**
     * 数组和List的映射
     */
    @Test
    public void convertListObject(){
       Person person = new Person();
        person.setNameParts(Lists.newArrayList("1","javadaily","jianzh5@163.com"));

        Map<String,String> refMap = new HashMap<>(2);
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("nameParts[0]","id");
        refMap.put("nameParts[1]","name");
        refMap.put("nameParts[2]","email");

        Student student = OrikaUtils.convert(person, Student.class,refMap);
        System.out.println(student);
    }


    /**
     * 类类型映射
     */
    @Test
    public void convertClassObject(){
        BasicPerson basicPerson = new BasicPerson();
        Student student = new Student("1","javadaily","jianzh5@163.com");
        basicPerson.setStudent(student);

        Map<String,String> refMap = new HashMap<>(2);
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("student.id","id");
        refMap.put("student.name","name");
        refMap.put("student.email","emailAddress");

        Teacher teacher = OrikaUtils.convert(basicPerson, Teacher.class,refMap);
        System.out.println(teacher);
    }




}
