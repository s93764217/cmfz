package com.baizhi.controller;

import com.baizhi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @RequestMapping("login")
    public @ResponseBody
    Object login(String phone, String password, String code) {
        return accountService.login(phone, password, code);
    }

    @RequestMapping("register")
    public @ResponseBody
    Object register(String phone, String password) {
        return accountService.register(phone, password);
    }
}
