package com.baizhi.service;


import com.baizhi.entity.User;
import com.baizhi.entity.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {
    int selectAllUser(int time);

    void register(User user);

    //    List<UserDTO> selectAllUserWithMap(int sex);
    List<UserVO> selectWithMap(int sex);

    Map insert(User user);

    Map selectAll(int page, int row);
}
