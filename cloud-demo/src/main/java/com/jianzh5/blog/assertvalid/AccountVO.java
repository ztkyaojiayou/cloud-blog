package com.jianzh5.blog.assertvalid;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author javadaily
 * @date 2022/1/9 10:19 下午
 */
@Data
public class AccountVO {
    @NotNull(message = "ID不能为空")
    private String id;

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "密码不能为空")
    @Length(min=6,message="密码长度不能小于6位")
    private String password;

    @Email(message = "请填写正取的邮箱地址")
    @NotNull(message = "邮件不允许为空")
    private String email;


}
