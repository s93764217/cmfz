package com.baizhi.controller;

import com.baizhi.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirstPageController {

    @Autowired
    private FirstPageService firstPageService;

    @RequestMapping("first_page")
    @ResponseBody
    public Object firstPage(Integer uid, String type, String sub_type) {
        return firstPageService.firstPage(uid, type, sub_type);
    }
}
