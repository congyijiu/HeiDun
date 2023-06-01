package com.congyijiu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.congyijiu.Exams;
import com.congyijiu.Users;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-05-31-13:05
 */
public interface UsersService extends IService<Users>{
    public List<Exams> getUsersAppointment(Long userId);
}
