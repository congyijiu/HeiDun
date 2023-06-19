package com.congyijiu.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.congyijiu.Dto.UsersDto;
import com.congyijiu.Exams;
import com.congyijiu.Users;
import com.congyijiu.Vo.UserVo;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.auth.service.UsersService;
import com.congyijiu.common.jwt.JwtHelper;
import com.congyijiu.common.md5.MD5;
import com.congyijiu.common.result.Result;
import com.google.code.kaptcha.Constants;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        users.setPassword(null);
        return Result.ok(users);
    }


    @ApiOperation("修改个人信息")
    @PutMapping("/updateUser")
    public Result updateUser(@RequestHeader("token") String token, @RequestBody UsersDto usersDto) {
        Long userId = JwtHelper.getUserId(token);
        Long id = usersDto.getId();
        if (!userId.equals(id)) {
            return Result.fail("无权限");
        }
        if (usersDto.getOldPassword() != null) {
            Users byId = usersService.getById(userId);
            String password = byId.getPassword();
            String oldPassword = usersDto.getOldPassword();
            oldPassword = MD5.encrypt(oldPassword);
            if (!password.equals(oldPassword)) {
                return Result.fail("密码错误");
            }
        }
        Users users = new Users();
        //密码加密
        usersDto.setPassword(MD5.encrypt(usersDto.getPassword()));
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


    @ApiOperation("用户考试成绩查询")
    @GetMapping("/getUsersexams")
    public Result getUsers(@RequestBody UserVo userVo) {
        String username = userVo.getUsername();
        String idcard = userVo.getIdcard();
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, username);
        wrapper.eq(Users::getIdcard, idcard);
        Users users = usersService.getOne(wrapper);
        if (users == null) {
            return Result.fail("用户不存在");
        }
        Long userId = users.getId();
        List<Exams> usersExams = usersService.getUsersAppointment(userId);
        ArrayList<Exams> examslist = new ArrayList<>();
        for (Exams exams : usersExams) {
            if (exams.getStatus()==2) {
                examslist.add(exams);
            }
        }
        return Result.ok(examslist.get(0));
    }

}
