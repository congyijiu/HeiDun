package com.congyijiu.auth.controller;

import com.congyijiu.Dto.UsersDto;
import com.congyijiu.Exams;
import com.congyijiu.Users;
import com.congyijiu.auth.service.UsersService;
import com.congyijiu.common.jwt.JwtHelper;
import com.congyijiu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ApiOperation("获取个人信息")
    @GetMapping("/getUser")
    public Result getUser(@RequestHeader("token") String token) {
        Long userId = JwtHelper.getUserId(token);
        Users users = usersService.getById(userId);
        return Result.ok(users);
    }


    @ApiOperation("修改个人信息")
    @PutMapping("/updateUser")
    public Result updateUser(@RequestHeader("token") String token, @RequestBody UsersDto usersDto) {
        Long userId = JwtHelper.getUserId(token);
        Long id = usersDto.getId();
        if(!userId.equals(id)) {
            return Result.fail("无权限");
        }
        if(usersDto.getOldPassword() == null) {
            Users byId = usersService.getById(userId);
            String password = byId.getPassword();
            if(!password.equals(usersDto.getPassword())) {
                return Result.fail("密码错误");
            }
        }
        Users users = new Users();
        BeanUtils.copyProperties(usersDto, users);
        usersService.updateById(users);
        return Result.ok();
    }

    @ApiOperation("获取用户预约信息")
    @GetMapping("/getUsersAppointment")
    public Result getUsersAppointment(@RequestHeader("token") String token) {
        Long userId = JwtHelper.getUserId(token);
        List<Exams> appointmentList = usersService.getUsersAppointment(userId);
        return Result.ok(appointmentList);
    }

}
