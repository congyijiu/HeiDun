package com.congyijiu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.congyijiu.Dto.ExamsDto;
import com.congyijiu.Exams;
import com.congyijiu.Questions;
import com.congyijiu.UserExams;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-05-31-13:04
 */

public interface ExamsService extends IService<Exams> {
    public ExamsDto startExam(Long userId,Long examId);

    public Long submitExams(List<UserExams> userExams);

    public ExamsDto randomExam(Long userId);

    public Long getExams(Long userId,Integer type);
}
