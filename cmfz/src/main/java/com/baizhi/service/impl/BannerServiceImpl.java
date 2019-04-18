package com.baizhi.service.impl;

import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Map selectAll(int page, int size) {
        Map map = new HashMap();
        PageHelper.startPage(page, size);
        PageInfo<Banner> pageInfo = new PageInfo<>(bannerMapper.selectAll());
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public void insert(Banner banner) {
        bannerMapper.insert(banner);
    }

    @Override
    public void updateStatus(Banner banner) {
        bannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void delete(int id) {
        bannerMapper.deleteByPrimaryKey(id);
    }
}
