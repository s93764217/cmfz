package com.baizhi.controller;

import com.baizhi.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("detail")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @RequestMapping("wen")
    public @ResponseBody
    Object wen(String id, Integer uid) {
        return detailsService.wen(id, uid);
    }
}
