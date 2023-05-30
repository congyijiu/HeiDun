package com.congyijiu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-05-30-23:35
 */
@Data
@TableName("users")
public class users {
    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Integer userId;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("idcard")
    private String idcard;
}
