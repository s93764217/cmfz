package com.baizhi.dao;


import com.baizhi.entity.User;
import com.baizhi.entity.UserVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    int selectAllUser(int time);

    List<UserVO> selectWithMap(int sex);
}

