package com.congyijiu.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.congyijiu.Dto.ExamsDto;
import com.congyijiu.Exams;
import com.congyijiu.Questions;
import com.congyijiu.UserExams;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.auth.service.UserExamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congyijiu
 * @create 2023-06-01-22:44
 */
@Api(tags = "用户考试")
@RestController
@RequestMapping("/userExams")
public class UserExamsController {
    @Autowired
    private UserExamsService userExamsService;

    @Autowired
    private ExamsService examsService;

    @ApiOperation("保存用户考试记录并完善考试记录")
    @PostMapping("/SaveUserExam")
    public void startExam(@RequestBody List<UserExams> userExams) {
        UserExams userExams1 = userExams.get(0);
        Long examId = userExams1.getExamId();
        LambdaQueryWrapper<UserExams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserExams::getExamId, examId);
        //删除原先不完整的用户考试详情记录
        userExamsService.remove(wrapper);
        //保存完整的用户考试详情记录
        userExamsService.saveBatch(userExams);
        //完善考试记录
        int correctNum = 0;
        for (UserExams userExam : userExams) {
            Integer correct = userExam.getCorrect();
            if (correct == 1) {
                correctNum++;
            }
        }
        int totalNum = userExams.size();
        Integer score = correctNum / totalNum;
        String result;
        if (score >=60){
            result = "通过";
        }else {
            result = "不通过";
        }
        Exams exams = new Exams();
        exams.setId(examId);
        exams.setScore(score);
        exams.setResult(result);
    }

    @ApiOperation("通过考试id获取用户考试记录")
    @PostMapping("/getUserExamByExamId")
    public List<UserExams> getUserExamByExamId(@RequestBody Exams exams) {
        Long examId = exams.getId();
        LambdaQueryWrapper<UserExams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserExams::getExamId, examId);
        List<UserExams> userExams = userExamsService.list(wrapper);
        return userExams;
    }
}
