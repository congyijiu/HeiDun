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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    //开始考试
    @Override
    public ExamsDto startExam(Long userId, Long examId) {
        List<Questions> randomQuestions = questionsService.getRandomQuestions();

        ExamsDto examsDto = new ExamsDto();
        examsDto.setQuestions(randomQuestions);
        examsDto.setExamId(examId);
        return examsDto;
    }

    //提交考试
    @Override
    public ExamsDto submitExams(Long userId, ExamsDto examsDto) {
        List<Questions> questions = examsDto.getQuestions();
        ArrayList<Long> qId = new ArrayList<>();
        for (Questions question : questions) {
            //获取考试题目编号集合
            Long id = question.getId();
            qId.add(id);
        }
        //查询题目答案解析等
        List<Questions> questionsList = questionsService.listByIds(qId);
        examsDto.setQuestions(questionsList);
        return examsDto;
    }

    //随机模拟考试
    @Override
    public ExamsDto randomExam(Long userId) {
        List<Questions> randomQuestions = questionsService.getRandomQuestions();
        ExamsDto examsDto = new ExamsDto();
        examsDto.setQuestions(randomQuestions);
        Long examsId = getExams(userId,0);
        examsDto.setExamId(examsId);
        return examsDto;
    }


    //生成考试记录
    @Override
    public Long getExams(Long userId,Integer type) {
        Exams exams = new Exams();
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String format = dateFormat.format(new Date());
        exams.setExamsTime(format);
        exams.setNumQuestions(15);
        exams.setType(type);
        exams.setUserId(userId);
        this.save(exams);
        return exams.getId();
    }


}
