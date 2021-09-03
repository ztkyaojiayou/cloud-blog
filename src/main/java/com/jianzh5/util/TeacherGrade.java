package com.jianzh5.util;

import lombok.Data;

import java.util.List;

/**
 * @author jam
 * @date 2021/9/2 4:15 下午
 */
@Data
public class TeacherGrade {
    private String teacherGradeName;
    private List<Teacher> teacherList;
}
