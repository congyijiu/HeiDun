package com.congyijiu.auth.controller;

import com.congyijiu.Dto.UsersDto;
import com.congyijiu.Exams;
import com.congyijiu.Users;
import com.congyijiu.auth.service.UsersService;
import com.congyijiu.common.jwt.JwtHelper;
import com.congyijiu.common.result.Result;
import io.swagger.annotations.Api;
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

    @GetMapping("/getUser")
    public Result getUser(@RequestHeader("token") String token) {
        Long userId = JwtHelper.getUserId(token);
        Users users = usersService.getById(userId);
        return Result.ok(users);
    }


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

    @GetMapping("/getUsersAppointment")
    public Result getUsersAppointment(@RequestHeader("token") String token) {
        Long userId = JwtHelper.getUserId(token);
        List<Exams> appointmentList = usersService.getUsersAppointment(userId);
        return Result.ok(appointmentList);
    }

}
