package com.congyijiu.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.congyijiu.Dto.ExamsDto;
import com.congyijiu.Exams;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.common.jwt.JwtHelper;
import com.congyijiu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        examsService.save(exams);
        return Result.ok();
    }

    @ApiOperation("开始考试")
    @PostMapping("/startExam/{examsTime}")
    public Result startExams(@RequestHeader("token") String token , @PathVariable String examsTime) {
        Long userId = JwtHelper.getUserId(token);
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Exams::getUserId, userId);
        wrapper.eq(Exams::getExamsTime, examsTime);
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
    public Result submitExams(@RequestHeader("token") String token , @PathVariable Long examsId) {
        Long userId = JwtHelper.getUserId(token);
        ExamsDto examsDto1 = examsService.submitExams(userId, examsId);
        return Result.ok(examsDto1);
    }

    @ApiOperation("随机模拟考试")
    @PostMapping("/randomExam")
    public Result randomExam(@RequestHeader("token") String token) {
        Long userId = JwtHelper.getUserId(token);
        ExamsDto examsDto = examsService.randomExam(userId);
        return Result.ok(examsDto);
    }

}
