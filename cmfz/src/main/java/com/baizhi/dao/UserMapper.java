package com.baizhi.dao;


import com.baizhi.entity.UserVO;

import java.util.List;

public interface UserMapper {
    int selectAllUser(int time);

    //    List<UserDTO> selectAllUserWithMap(int sex);
    List<UserVO> selectWithMap(int sex);
}

