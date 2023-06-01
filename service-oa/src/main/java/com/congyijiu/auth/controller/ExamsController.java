package com.congyijiu.auth.controller;

import com.congyijiu.Exams;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.common.jwt.JwtHelper;
import com.congyijiu.common.result.Result;
import io.swagger.annotations.Api;
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

    @PostMapping("/registeExams")
    public Result registeExams(@RequestHeader("token") String token , @RequestBody Exams exams) {
        Long userId = JwtHelper.getUserId(token);
        exams.setUserId(userId);
        examsService.save(exams);
        return Result.ok();
    }

    @PostMapping("/startExams")
    public Result startExams(@RequestHeader("token") String token , @RequestBody Exams exams) {
        Long userId = JwtHelper.getUserId(token);

        return Result.ok();
    }
}
