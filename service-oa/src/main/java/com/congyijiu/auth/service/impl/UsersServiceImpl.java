package com.congyijiu.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.congyijiu.Users;
import com.congyijiu.auth.mapper.UsersMapper;
import com.congyijiu.auth.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * @author congyijiu
 * @create 2023-05-31-13:10
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
}
