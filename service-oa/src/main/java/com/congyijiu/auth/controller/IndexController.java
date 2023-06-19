package com.congyijiu.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.congyijiu.Users;
import com.congyijiu.Vo.UserVo;
import com.congyijiu.auth.service.UsersService;
import com.congyijiu.common.jwt.JwtHelper;
import com.congyijiu.common.md5.MD5;
import com.congyijiu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author congyijiu
 * @create 2023-05-31-13:12
 */
@Api(tags = "首页")
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UsersService usersService;


    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserVo loginVo) {

        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        password  = MD5.encrypt(password);// 加密(不可逆)

        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, username);
        Users users = usersService.getOne(wrapper);
        if (users == null) {
            return Result.fail("用户名不存在");
        }
        if (!users.getPassword().equals(password)) {
            return Result.fail("密码错误");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(users.getId(), users.getUsername()));
        return Result.ok(map);
    }

}
