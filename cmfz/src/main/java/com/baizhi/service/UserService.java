package com.baizhi.service;


import com.baizhi.entity.UserVO;

import java.util.List;

public interface UserService {
    int selectAllUser(int time);

    //    List<UserDTO> selectAllUserWithMap(int sex);
    List<UserVO> selectWithMap(int sex);
}
