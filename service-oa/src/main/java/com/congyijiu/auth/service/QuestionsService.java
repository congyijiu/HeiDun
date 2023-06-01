package com.congyijiu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.congyijiu.Questions;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-05-31-13:07
 */
public interface QuestionsService extends IService<Questions> {
    public List<Questions> getRandomQuestions();
}
