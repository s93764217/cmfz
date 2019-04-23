package com.baizhi.controller;


import com.baizhi.entity.UserVO;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("selectAllUserByBar")
    @ResponseBody
    public Map selectAllUserByBar() {
        Map<String, Object> map = new HashMap<>();

        int num1 = userService.selectAllUser(7);
        int num2 = userService.selectAllUser(14);
        int num3 = userService.selectAllUser(21);
        map.put("counts", new int[]{num1, num2, num3});
        map.put("intervals", new String[]{"过去一周", "过去两周", "过去三周"});
        return map;
    }

    //    @RequestMapping("selectAllUserByMap")
//    @ResponseBody
//    public List<UserDTO> selectAllUserByMap(int sex){
//        return userService.selectAllUserWithMap(sex);
//    }
    @RequestMapping("selectWithMap")
    @ResponseBody
    public List<UserVO> selectWithMap(int sex) {
        return userService.selectWithMap(sex);
    }

}
