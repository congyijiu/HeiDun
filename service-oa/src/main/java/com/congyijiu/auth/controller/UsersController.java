package com.congyijiu.auth.controller;

import com.congyijiu.Users;
import com.congyijiu.auth.service.UsersService;
import com.congyijiu.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author congyijiu
 * @create 2023-05-31-13:23
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getUserByid")
    public Result getUserByid() {
        Users users = usersService.getById(1L);
        return Result.ok(users);
    }

}
