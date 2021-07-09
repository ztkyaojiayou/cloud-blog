package com.jianzh5.blog.map;

import lombok.Data;

/**
 * @author jam
 * @date 2021/7/5 10:45 上午
 */
@Data
public class Aniaml {
    private int id;
    private String name;

    public Aniaml(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
