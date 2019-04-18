package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

public interface BannerService {
    Map selectAll(int page, int size);

    void insert(Banner banner);

    void updateStatus(Banner banner);

    void delete(int id);
}
