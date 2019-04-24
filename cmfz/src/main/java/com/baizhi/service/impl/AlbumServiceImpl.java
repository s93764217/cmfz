package com.baizhi.service.impl;

import com.baizhi.dao.AlbumMapper;
import com.baizhi.dao.ChapterMapper;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public Map selectAllAlbum(int page, int rows) {
        Map map = new HashMap();
        PageHelper.startPage(page, rows);
        PageInfo<Album> pageInfo = new PageInfo<>(albumMapper.selectAllAlbum());
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public void insert(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public List<Album> selectAll() {
        return albumMapper.selectAll();
    }

    @Override
    public void insertChapter(Chapter chapter) {
        chapterMapper.insert(chapter);
    }
}
