package com.jianzh5.limit.exception;

/**
 * @author jam
 * 公众号：JAVA日知录
 * 自定义限流器异常
 * @date 2023/3/14 19:49
 */
public class LimiterException extends RuntimeException{

    public LimiterException(String msg) {
        super( msg );
    }
}
