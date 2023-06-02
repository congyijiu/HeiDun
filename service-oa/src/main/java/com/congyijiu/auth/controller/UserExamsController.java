package com.congyijiu.auth.controller;

import com.congyijiu.Dto.ExamsDto;
import com.congyijiu.Questions;
import com.congyijiu.UserExams;
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

    @ApiOperation("保存用户考试记录并完善考试记录")
    @PostMapping("/SaveUserExam")
    public void startExam(@RequestBody ExamsDto examsDto) {
        //保存用户考试题目和正确情况
        ArrayList<UserExams> userExams = new ArrayList<>();
        List<Questions> questions = examsDto.getQuestions();
        Long examId = examsDto.getExamId();
        int num = questions.size();
        int tureNum = 0;
        for (Questions question : questions) {
            //获取正确情况0错误1正确
            Integer correct = question.getCorrect();
            if(correct == 1) {
                tureNum++;
            }
            //保存用户考试记录
            UserExams userExam = new UserExams();
            userExam.setExamId(examsDto.getExamId());
            userExam.setQuestionId(question.getId());
            userExam.setCorrect(correct);
            userExam.setId(0L);
            userExams.add(userExam);
        }
        int score = (int) ((tureNum * 1.0 / num) * 100);
        //完善考试信息
        userExamsService.submitExam(examId,score);
        //保存用户考试记录
        userExamsService.saveBatch(userExams);
    }
}
