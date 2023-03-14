package com.jianzh5.blog.async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author jam
 * @date 2021/11/25 3:15 下午
 */
@Component
@Slf4j
public class AsyncTask {

    @SneakyThrows
    @Async
    public void doTask1() {
        long t1 = System.currentTimeMillis();
        Thread.sleep(2000);
        long t2 = System.currentTimeMillis();
        log.info("task1 cost {} ms" , t2-t1);
    }

    @SneakyThrows
    @Async("otherTaskExecutor")
    public void doTask2() {
        long t1 = System.currentTimeMillis();
        Thread.sleep(3000);
        long t2 = System.currentTimeMillis();
        log.info("task2 cost {} ms" , t2-t1);
    }
}
