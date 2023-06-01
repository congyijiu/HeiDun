package com.congyijiu.auth.controller;

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
    @PostMapping("/startExam/{examsId}")
    public Result startExams(@RequestHeader("token") String token , Long examsId) {
        Long userId = JwtHelper.getUserId(token);
        ExamsDto examsDto = examsService.startExam(userId, examsId);
        return Result.ok(examsDto);
    }

    @ApiOperation("提交试卷")
    @PostMapping("/submitExams")
    public Result submitExams(@RequestHeader("token") String token , @RequestBody ExamsDto examsDto) {
        Long userId = JwtHelper.getUserId(token);
        ExamsDto examsDto1 = examsService.submitExams(userId, examsDto);
        return Result.ok(examsDto1);
    }


}
