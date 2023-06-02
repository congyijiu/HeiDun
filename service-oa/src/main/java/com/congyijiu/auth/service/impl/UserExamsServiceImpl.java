package com.congyijiu.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.congyijiu.Exams;
import com.congyijiu.UserExams;
import com.congyijiu.auth.mapper.UserExamsMapper;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.auth.service.UserExamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PrinterURI;

/**
 * @author congyijiu
 * @create 2023-05-31-13:09
 */
@Service
public class UserExamsServiceImpl extends ServiceImpl<UserExamsMapper, UserExams> implements UserExamsService {

    @Autowired
    private ExamsService examsService;

    @Override
    public void submitExam(Long examId, int score) {
        Exams exams = new Exams();
        exams.setId(examId);
        exams.setScore(score);
        if(score>=60) {
            exams.setResult("通过");
        }else {
            exams.setResult("未通过");
        }
        examsService.updateById(exams);
    }
}
