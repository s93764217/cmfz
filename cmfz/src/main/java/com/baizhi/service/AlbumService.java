package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.List;
public interface AlbumService {
    List<Album> selectAllAlbum();

    void insert(Album album);

    List<Album> selectAll();

    void insertChapter(Chapter chapter);
}
