package com.congyijiu.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.congyijiu.Exams;
import com.congyijiu.Users;
import com.congyijiu.auth.mapper.UsersMapper;
import com.congyijiu.auth.service.ExamsService;
import com.congyijiu.auth.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-05-31-13:10
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Autowired
    private ExamsService examsService;

    public List<Exams> getUsersAppointment(Long id) {
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Exams::getUserId, id);
        wrapper.eq(Exams::getType,0);
        List<Exams> list = examsService.list(wrapper);
        return list;
    }
}
