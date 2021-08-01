package com.jianzh5.blog.valid;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author jam
 * @date 2021/8/1 7:21 下午
 */
@Data
@ApiModel(value = "ValidEntity")
public class ValidEntity {
    private int id;

    @NotBlank(groups = ValidGroup.Crud.Update.class)
    private String appId;

    @NotBlank(groups = ValidGroup.Crud.Create.class)
    private String name;

    @Email
    private String email;

}
