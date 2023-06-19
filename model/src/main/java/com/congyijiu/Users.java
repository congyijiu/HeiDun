package com.congyijiu;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-05-30-23:35
 */

@Data
@ApiModel(value="用户对象", description="")
@TableName("Users")
public class Users {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    @TableField(value = "username",updateStrategy = FieldStrategy.NOT_EMPTY)
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField(value = "password",updateStrategy = FieldStrategy.NOT_EMPTY)
    private String password;

    @ApiModelProperty(value = "用户电话")
    @TableField(value = "phone",updateStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;

    @ApiModelProperty(value = "用户邮箱")
    @TableField(value = "email",updateStrategy = FieldStrategy.NOT_EMPTY)
    private String email;

    @ApiModelProperty(value = "用户身份证")
    @TableField(value = "idcard",updateStrategy = FieldStrategy.NOT_EMPTY)
    private String idcard;
}
