package com.jianzh5.blog.util;

import java.util.Random;

/**
 * @author jam
 * 获取枚举中的随机实例
 * 公众号：JAVA日知录
 * @date 2022/5/2 09:25
 */
public class EnumsUtil {
    private static final Random RAND = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }

    private static <T> T random(T[] values) {
        return values[RAND.nextInt(values.length)];
    }


    enum Activety{String,Test,Java}

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(EnumsUtil.random(Activety.class) + " ");
        }
    }
}
