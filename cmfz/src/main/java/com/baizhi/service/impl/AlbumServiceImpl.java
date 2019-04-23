package com.baizhi.service.impl;

import com.baizhi.dao.AlbumMapper;
import com.baizhi.dao.ChapterMapper;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public List<Album> selectAllAlbum() {
        /*Map map = new HashMap();
        PageHelper.startPage(page, size);
        PageInfo<Album> pageInfo = new PageInfo<>(albumMapper.selectAllAlbum());
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());*/
        System.err.println("album in service");
        return albumMapper.selectAllAlbum();
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
