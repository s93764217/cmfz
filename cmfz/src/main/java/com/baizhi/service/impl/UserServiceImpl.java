package com.baizhi.service.impl;


import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserVO;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.goeasy.GoEasy;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int selectAllUser(int time) {
        return userMapper.selectAllUser(time);
    }

    @Override
    public void register(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<UserVO> selectWithMap(int sex) {
        return userMapper.selectWithMap(sex);
    }

    @Override
    public Map insert(User user) {
        Map map = new HashMap();
        try {
            String password = user.getPassword();
            String salt = UUID.randomUUID().toString().replaceAll("-", "").substring(3, 8);
            password = DigestUtils.md5Hex(password + salt);
            user.setSalt(salt);
            user.setPassword(password);
            user.setGuruId(1);
            user.setRegisterTime(new Date());
            user.setStatus(0);
            user.setCity("郑州");
            user.setProvince("河南");
            userMapper.insert(user);
            map.put("isRegister", true);
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-6a167257a006457e806a2ed2dfc88ba8");
            goEasy.publish("wq", "Hello, GoEasy!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isRegister", false);
        }
        return map;
    }

    @Override
    public Map selectAll(int page, int row) {
        Map map = new HashMap();
        PageHelper.startPage(page, row);
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectAll());
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

//    @Override
//    public List<UserDTO> selectAllUserWithMap(int sex) {
//        return userMapper.selectAllUserWithMap(sex);
//    }
}
