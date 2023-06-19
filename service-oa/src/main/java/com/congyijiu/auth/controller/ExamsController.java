package com.congyijiu.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.congyijiu.Dto.ExamsDto;
import com.congyijiu.Exams;
import com.congyijiu.UserExams;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.common.jwt.JwtHelper;
import com.congyijiu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-05-31-18:22
 */
@Api(tags = "考试")
@RestController
@RequestMapping("/exams")
public class ExamsController {

    @Autowired
    private ExamsService examsService;

    @ApiOperation("报名考试")
    @PostMapping("/registeExams")
    public Result registeExams(@RequestHeader("token") String token , @RequestBody Exams exams) {
        Long userId = JwtHelper.getUserId(token);
        exams.setUserId(userId);
        String subject = exams.getSubject();
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Exams::getUserId, userId);
        wrapper.eq(Exams::getSubject, subject);
        Exams exams1 = examsService.getOne(wrapper);
        if(exams1 != null) {
            return Result.fail("已经报名该考试");
        }
        examsService.save(exams);
        return Result.ok();
    }

    @ApiOperation("开始考试")
    @PostMapping("/startExam/{subject}")
    public Result startExams(@RequestHeader("token") String token , @PathVariable String subject) {
        Long userId = JwtHelper.getUserId(token);
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Exams::getUserId, userId);
        wrapper.eq(Exams::getSubject, subject);
        Exams exams = examsService.getOne(wrapper);
        if(exams == null) {
            return Result.fail("没有报名该考试");
        }
        Long examsId = exams.getId();
        ExamsDto examsDto = examsService.startExam(userId, examsId);
        return Result.ok(examsDto);
    }

    @ApiOperation("提交试卷")
    @PostMapping("/submitExams/{examsId}")
    public Result submitExams(@RequestHeader("token") String token , @RequestBody List<UserExams> userExams) {
        Long userId = JwtHelper.getUserId(token);
        Long examsId = examsService.submitExams(userExams);
        return Result.ok(examsId);
    }

    @ApiOperation("随机模拟考试")
    @PostMapping("/randomExam")
    public Result randomExam(@RequestHeader("token") String token) {
        Long userId = JwtHelper.getUserId(token);
        ExamsDto examsDto = examsService.randomExam(userId);
        return Result.ok(examsDto);
    }

    @ApiOperation("通过用户id获取考试列表")
    @GetMapping("/getExamsList")
    public Result getExamsList(@RequestHeader("token") String token) {
        Long userId = JwtHelper.getUserId(token);
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Exams::getUserId, userId);
        wrapper.orderByDesc(Exams::getStartTime);
        return Result.ok(examsService.list(wrapper));
    }

    @ApiOperation("通过考试名查询用户是否报名此考试")
    @GetMapping("/isregisteByName/{subject}")
    public Result isregisteByName(@RequestHeader("token") String token , @PathVariable String subject) {
        Long userId = JwtHelper.getUserId(token);
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Exams::getUserId, userId);
        wrapper.eq(Exams::getSubject, subject);
        Exams exams = examsService.getOne(wrapper);
        if(exams == null) {
            return Result.ok(false);
        }
        return Result.ok(true);
    }
}
