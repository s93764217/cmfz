package com.baizhi.service.impl;

import com.baizhi.dao.UserMapper;
import com.baizhi.entity.Error;
import com.baizhi.entity.User;
import com.baizhi.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object login(String phone, String password, String code) {
        if (phone == null) {
            return new Error("手机号都不输，你登啥登");
        } else {
            if (password == null && code == null) {
                return new Error("密码,验证码都为空，你登个鸡儿");
            } else {
                User user = new User();
                user.setPhone(phone);
                String salt = userMapper.selectOne(user).getSalt();
                password = DigestUtils.md5Hex(password + salt);
                if (password != null || "1234".equals(code)) {
                    return userMapper.selectOne(user);
                } else {
                    return new Error("密码或者验证码错误");
                }
            }
        }
    }

    @Override
    public Object register(String phone, String password) {
        User user = new User();
        if (phone == null || password == null) {
            return new Error("手机号或者密码不能为空");
        } else {
            user.setPhone(phone);
            if (userMapper.select(user).size() != 0) {
                return new Error("用户名已存在");
            } else {
                user.setPhone(phone);
                user.setPassword(password);
                user.setGuruId(1);
                userMapper.insert(user);
                return userMapper.selectOne(user);
            }
        }
    }
}
