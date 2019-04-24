package com.baizhi.service.impl;

import com.baizhi.dao.AlbumMapper;
import com.baizhi.dao.ChapterMapper;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.Error;
import com.baizhi.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public Object wen(String id, Integer uid) {
        if (id == null || uid == null) {
            return new Error("用户名ID或者专辑ID为空");
        } else {
            Map map = new HashMap<>();
            map.put("introduction", albumMapper.selectByPrimaryKey(id));
            Chapter chapter = new Chapter();
            chapter.setAlbumId(id);
            map.put("list", chapterMapper.select(chapter));
            return map;
        }

    }
}
