package com.jianzh5.limit.manager;

import lombok.Builder;
import lombok.Data;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/3/14 19:42
 */
@Data
@Builder
public class Limiter {

    /**
     * 资源的key,唯一
     * 作用：不同的接口，不同的流量控制
     */
    private String key;

    /**
     * 最多的访问限制次数
     */
    private int limitNum;

    /**
     * 多少秒
     */
    private int seconds;

}
