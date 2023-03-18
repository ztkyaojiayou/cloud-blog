package com.jianzh5.limit.manager;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/3/14 19:46
 */
public interface LimiterManager {
    boolean tryAccess(Limiter limiter);
}
