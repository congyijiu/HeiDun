package com.congyijiu.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.congyijiu.UserQuestions;
import com.congyijiu.auth.mapper.UserQuestionsMapper;
import com.congyijiu.auth.service.UserQuestionsService;
import org.springframework.stereotype.Service;

/**
 * @author congyijiu
 * @create 2023-05-31-13:09
 */
@Service
public class UserQuestionsServiceImpl extends ServiceImpl<UserQuestionsMapper, UserQuestions> implements UserQuestionsService {
}
