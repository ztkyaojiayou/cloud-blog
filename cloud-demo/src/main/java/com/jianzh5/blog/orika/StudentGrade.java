package com.jianzh5.blog.orika;

import lombok.Data;

import java.util.List;

/**
 * @author jam
 * @date 2021/9/2 4:15 下午
 */
@Data
public class StudentGrade {
    private String studentGradeName;
    private List<Student> studentList;
}
