package com.baizhi.service.impl;


import com.baizhi.dao.UserMapper;
import com.baizhi.entity.UserVO;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int selectAllUser(int time) {
        return userMapper.selectAllUser(time);
    }

    @Override
    public List<UserVO> selectWithMap(int sex) {
        return userMapper.selectWithMap(sex);
    }

//    @Override
//    public List<UserDTO> selectAllUserWithMap(int sex) {
//        return userMapper.selectAllUserWithMap(sex);
//    }
}
