package com.jianzh5.blog.test;

import java.util.EnumMap;
import java.util.Map;

import static com.jianzh5.blog.test.AlarmPoints.*;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2022/5/2 20:58
 */
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints,Command> em = new EnumMap<>(AlarmPoints.class);

        em.put(KITCHEN,()-> System.out.println("Kitchen Fire"));
        em.put(BATHROOM,()-> System.out.println("Bathroom alert"));

        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            System.out.println(e.getKey() + ":") ;
            e.getValue().action();
        }

        em.get(UTILITY).action();


    }
}
