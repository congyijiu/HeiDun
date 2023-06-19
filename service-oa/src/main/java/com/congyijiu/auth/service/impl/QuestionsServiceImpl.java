package com.congyijiu.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.congyijiu.Questions;
import com.congyijiu.auth.mapper.QuestionsMapper;
import com.congyijiu.auth.service.QuestionsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author congyijiu
 * @create 2023-05-31-13:09
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsService {

    @Override
    public List<Questions> getRandomQuestions() {

        QueryWrapper<Questions> wrapper = new QueryWrapper<>();
        wrapper.select("id", "description", "options")
                .orderByAsc("RAND()")
                .last("limit 25");
        List<Questions> questionsList = this.list(wrapper);
        return questionsList;
    }
}
