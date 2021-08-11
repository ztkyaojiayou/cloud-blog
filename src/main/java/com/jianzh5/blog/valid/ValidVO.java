package com.jianzh5.blog.valid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author jam
 * @date 2021/8/1 7:21 下午
 */
@Data
@ApiModel(value = "参数校验类")
public class ValidVO {

    @ApiModelProperty("ID")
    @Null(groups = ValidGroup.Crud.Create.class)
    @NotNull(groups = ValidGroup.Crud.Update.class,message = "ID不能为空")
    private String id;

    @Null(groups = ValidGroup.Crud.Create.class)
    @NotNull(groups = ValidGroup.Crud.Update.class,message = "应用ID不能为空")
    @ApiModelProperty(value = "应用ID",example = "cloud")
    private String appId;

    @ApiModelProperty(value = "名字")
    @NotBlank(groups = ValidGroup.Crud.Create.class,message = "名字为必填项")
    private String name;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "请填写正取的邮箱地址")
    private String email;

    @ApiModelProperty(value = "性别")
    @EnumString(value = {"F","M"},message = "性别只允许为F或M")
    private String sex;

    @NotEmpty(message = "级别不能为空")
    @ApiModelProperty(value = "级别")
    private String level;

    @ApiModelProperty(value = "年龄")
//    @Null(groups = ValidGroup.Crud.Create.class)
//    @NotNull(groups = ValidGroup.Crud.Update.class)
    private int age;

}
