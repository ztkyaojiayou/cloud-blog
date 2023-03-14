package com.jianzh5.blog.limit;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * java日知录专用抽奖程序
 * @author javadaily
 */
public class LotteryClient {
    public static void main(String[] args) {
        int minValue = 1;
        int maxValue = 9;
        System.out.println("《Java高并发核心编程》中奖幸运数为：");

        IntStream intRandom = new Random()
                .ints(2, minValue, maxValue)
                .sorted();

        intRandom.forEach(System.out::println);
    }

}
