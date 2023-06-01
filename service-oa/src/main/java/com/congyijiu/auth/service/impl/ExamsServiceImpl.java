package com.congyijiu.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.congyijiu.Dto.ExamsDto;
import com.congyijiu.Exams;
import com.congyijiu.Questions;
import com.congyijiu.UserExams;
import com.congyijiu.auth.mapper.ExamsMapper;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.auth.service.QuestionsService;
import com.congyijiu.auth.service.UserExamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congyijiu
 * @create 2023-05-31-13:07
 */
@Service
public class ExamsServiceImpl extends ServiceImpl<ExamsMapper, Exams> implements ExamsService{

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private UserExamsService userExamsService;

    @Override
    public ExamsDto startExam(Long userId, Long examId) {
        List<Questions> randomQuestions = questionsService.getRandomQuestions();

        ExamsDto examsDto = new ExamsDto();
        examsDto.setQuestions(randomQuestions);
        examsDto.setExamId(examId);
        ArrayList<UserExams> userExams = new ArrayList<>();

        for (Questions question : randomQuestions) {
            UserExams userExam = new UserExams();
            userExam.setExamId(examId);
            userExam.setQuestionId(question.getId());
            userExams.add(userExam);
        }

        userExamsService.saveBatch(userExams);

        return examsDto;
    }
}
