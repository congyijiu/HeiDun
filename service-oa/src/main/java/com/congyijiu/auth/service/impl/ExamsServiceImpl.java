package com.congyijiu.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.web.bind.annotation.RequestBody;

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
        ArrayList<UserExams> userExams = new ArrayList<>();
        for (Questions randomQuestion : randomQuestions) {
            UserExams userExam = new UserExams();
            userExam.setExamId(examId);
            userExam.setQuestionId(randomQuestion.getId());
            userExams.add(userExam);
        }
        Exams exams = new Exams();
        exams.setId(examId);
        //设置考试状态：1-考试中
        exams.setStatus(1);
        this.updateById(exams);
        //保存考试记录
        userExamsService.saveBatch(userExams);
        ExamsDto examsDto = new ExamsDto();
        examsDto.setQuestions(randomQuestions);
        examsDto.setExamId(examId);
        return examsDto;
    }

    //提交考试
    @Override
    public Long submitExams(List<UserExams> userExams) {
        //获取当前考试id
        UserExams userExams1 = userExams.get(0);
        Long examId = userExams1.getExamId();
        LambdaQueryWrapper<UserExams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserExams::getExamId, examId);
        //删除原先不完整的用户考试详情记录
        userExamsService.remove(wrapper);

        ArrayList<Long> qId = new ArrayList<>();
        for (UserExams userExam : userExams) {
            Long questionId = userExam.getQuestionId();
            qId.add(questionId);
        }

        //查询题目答案解析等
        List<Questions> questionsList = questionsService.listByIds(qId);

        //正确题目数量
        int correctNum = 0;
        //完善用户考试详情记录
        for (int i =0;i<userExams.size();i++) {
            UserExams userExam = userExams.get(i);
            Questions questions = questionsList.get(i);
            Integer answer = questions.getAnswer();
            Integer selected = userExam.getSelected();
            if (answer.equals(selected)) {
                userExam.setCorrect(1);
                correctNum++;
            }else {
                userExam.setCorrect(0);
            }
        }

        //保存完整的用户考试详情记录
        userExamsService.saveBatch(userExams);

        //完善考试记录
        Integer score = correctNum * 4;
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
        exams.setEndTime(DateFormat.getDateTimeInstance().format(new Date()));
        //设置考试状态：2-考试结束
        exams.setStatus(2);
        //保存考试记录
        this.updateById(exams);
        return examId;
    }

    @Override
    public ExamsDto randomExam(Long userId) {
        List<Questions> randomQuestions = questionsService.getRandomQuestions();
        ExamsDto examsDto = new ExamsDto();
        examsDto.setQuestions(randomQuestions);
        Long examsId = getExams(userId,0);
        examsDto.setExamId(examsId);
        ArrayList<UserExams> userExams = new ArrayList<>();
        for (Questions randomQuestion : randomQuestions) {
            UserExams userExam = new UserExams();
            userExam.setExamId(examsId);
            userExam.setQuestionId(randomQuestion.getId());
            userExams.add(userExam);
        }
        //保存考试记录
        userExamsService.saveBatch(userExams);
        return examsDto;
    }


    //生成考试记录
    @Override
    public Long getExams(Long userId,Integer type) {
        Exams exams = new Exams();
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String format = dateFormat.format(new Date());
        exams.setStartTime(format);
        exams.setNumQuestions(15);
        exams.setType(type);
        exams.setUserId(userId);
        this.save(exams);
        return exams.getId();
    }


}
