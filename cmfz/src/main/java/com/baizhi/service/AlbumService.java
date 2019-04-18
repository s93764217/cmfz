package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map selectAllAlbum(int page, int size);

    void insert(Album album);

    List<Album> selectAll();

    void insertChapter(Chapter chapter);
}
