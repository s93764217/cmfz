package com.baizhi.service;

public interface AccountService {

    Object login(String phone, String password, String code);

    Object register(String phone, String password);
}
