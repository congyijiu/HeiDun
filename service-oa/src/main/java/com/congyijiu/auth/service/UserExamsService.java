package com.congyijiu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.congyijiu.UserExams;

/**
 * @author congyijiu
 * @create 2023-05-31-13:06
 */
public interface UserExamsService extends IService<UserExams>{

    public void submitExam(Long examId, int score);
}
