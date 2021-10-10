package com.jianzh5.blog.orika;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jam
 * @date 2021/9/2 2:51 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String id;
    private String name;
    private String emailAddress;
}
