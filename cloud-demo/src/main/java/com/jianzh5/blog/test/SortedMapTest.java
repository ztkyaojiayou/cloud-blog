package com.jianzh5.blog.test;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/1/12 09:16
 */
public class SortedMapTest {
    public static void main(String[] args) {
        Map<String,String> maps = new HashMap<>();
        maps.put("password","123456");
        maps.put("aname","jianzh5");

        System.out.println(maps);

        SortedMap<String,String> sortedMap = new TreeMap<>();

        for (Map.Entry<String,String> entry : maps.entrySet()) {
            sortedMap.put( entry.getKey(), entry.getValue());
        }

        System.out.println(sortedMap);

    }
}
