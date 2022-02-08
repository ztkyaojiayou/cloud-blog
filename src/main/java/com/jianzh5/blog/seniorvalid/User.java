package com.jianzh5.blog.seniorvalid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author javadaily
 * 高级业务参数校验
 * @date 2022/1/9 10:27 下午
 */
@Data
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @Length(min=6,message="密码长度不能小于6位")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @NotEmpty(message = "真实姓名不能为空")
    private String realName;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "1\\d{10}", message = "手机号格式不正确")
    private String telphone;


}
