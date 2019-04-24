package com.baizhi.service.impl;

import com.baizhi.dao.AlbumMapper;
import com.baizhi.dao.ArticleMapper;
import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Error;
import com.baizhi.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class FirstPageServiceImpl implements FirstPageService {

    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Object firstPage(Integer uid, String type, String subType) {
        if (uid == null || type == null) {
            return new Error("用户id或着类型为空");
        } else {
            Map map = new HashMap();
            if (type.equals("all")) {
                map.put("header", bannerMapper.selectAll());
                map.put("body", albumMapper.selectAll());
                map.put("article", articleMapper.selectArticleByUid(uid));
            } else if (type.equals("wen")) {
                map.put("body", albumMapper.selectAll());
            } else if (type.equals("si")) {
                if (subType == null) {
                    return new Error("subType类型为空");
                } else {
                    if (subType.equals("ssyj")) {
                        map.put("article", articleMapper.selectArticleByUid(uid));
                    } else if (subType.equals("xmfy")) {
                        map.put("article", articleMapper.selectAll());
                    } else {
                        return new Error("subType类型错误");
                    }
                }
            } else {
                return new Error("type类型错误");
            }
            return map;
        }
    }
}
