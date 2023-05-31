package com.congyijiu.auth;

import com.congyijiu.auth.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author congyijiu
 * @create 2023-05-31-12:57
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void test() {
        System.out.println(usersMapper.selectById(1));
    }

}
