package com.congyijiu.Dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-05-31-18:18
 */
@Data
public class UsersDto {
    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("username")
    private String username;

    private String oldPassword;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("idcard")
    private String idcard;
}
