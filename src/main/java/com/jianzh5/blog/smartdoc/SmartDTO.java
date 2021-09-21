package com.jianzh5.blog.smartdoc;

import com.jianzh5.blog.valid.EnumString;
import com.jianzh5.blog.valid.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author jam
 * @date 2021/8/1 7:21 下午
 */
@Data
public class SmartDTO {

    /**
     * id 新增时为空，编辑时必填
     */
    @Null(groups = ValidGroup.Crud.Create.class)
    @NotNull(groups = ValidGroup.Crud.Update.class,message = "ID不能为空")
    private String id;

    /**
     * 应用ID
     * 新增时为空，编辑时必填
     */
    @Null(groups = ValidGroup.Crud.Create.class)
    @NotNull(groups = ValidGroup.Crud.Update.class,message = "应用ID不能为空")
    private String appId;


    /**
     * 性别
     * @mock F
     */
    @EnumString(value = {"F","M"},message = "性别只允许为F或M")
    private String sex;

    /**
     * 级别
     * @mock A
     */
    @NotNull(message = "级别不能为空")
    private String level;


    /**
     * 年龄，编辑时为空，新增时必填
     */
    @Null(groups = ValidGroup.Crud.Update.class,message = "年龄必须为空")
    @NotNull(groups = ValidGroup.Crud.Create.class)
    private Integer age;

}
