package com.baizhi;

import com.baizhi.dao.AlbumMapper;
import com.baizhi.dao.MenuMapper;
import com.baizhi.entity.Album;
import com.baizhi.entity.Menu;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {


    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void contextLoads() {
        List<Menu> menus = menuMapper.queryAll();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    @Autowired
    private BannerService bannerService;

    @Test
    public void testBanner() {
        bannerService.selectAll(1, 2);
        //System.out.println(bannerLimit);

    }

    @Autowired
    private AlbumMapper albumMapper;

    @Test
    public void testAlbum() {
        List<Album> albums = albumMapper.selectAllAlbum();
        for (Album album : albums) {
            System.out.println("album = " + album);
        }
    }

    @Test
    public void Test01() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("id = " + id);
    }
}
