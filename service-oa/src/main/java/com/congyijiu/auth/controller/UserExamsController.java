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

    @ApiOperation("保存用户考试记录")
    @PostMapping("/SaveUserExam")
    public void startExam(@RequestBody ExamsDto examsDto) {
        ArrayList<UserExams> userExams = new ArrayList<>();
        List<Questions> questions = examsDto.getQuestions();
        Long examId = examsDto.getExamId();
        for (Questions question : questions) {
            //保存用户考试记录
            UserExams userExam = new UserExams();
            userExam.setExamId(examsDto.getExamId());
            userExam.setQuestionId(question.getId());
            userExam.setCorrect(question.getCorrect());
            userExam.setId(0L);
            userExams.add(userExam);
        }
        userExamsService.saveBatch(userExams);
    }
}
