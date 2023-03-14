package com.jianzh5.blog.sign;

import lombok.Builder;
import lombok.Data;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/1/15 15:41
 */
@Data
@Builder
public class RequestHeader {
   private String sign ;
   private Long timestamp ;
   private String nonce;
}
