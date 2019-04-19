package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.util.AudioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("selectAllAlbum")
    public @ResponseBody
    Map selectAllAlbum(int rows, int page) {
        return albumService.selectAllAlbum(page, rows);
    }

    @RequestMapping("insert")
    public @ResponseBody
    Map insertAlbum(Album album, MultipartFile file1) {
        Map map = null;
        try {
            map = new HashMap();
            String fileName = file1.getOriginalFilename();
            String filePath = "D:/cmfz/cmfz/src/main/webapp/jsp/album/img/";
            File dest = new File(filePath + fileName);
            file1.transferTo(dest);
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            album.setId(id);
            album.setImgPath(fileName);
            album.setReleaseDate(new Date());
            albumService.insert(album);
            map.put("isInsert", true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("isInsert", false);
        }

        return map;
    }

    @RequestMapping("selectAll")
    public @ResponseBody
    List<Album> selectAll() {
        return albumService.selectAll();
    }

    @RequestMapping("insertChapter")
    public @ResponseBody
    Map insertChapter(String ctitle, String id, MultipartFile file1) {
        Map map = null;
        try {
            map = new HashMap();
            String fileName = file1.getOriginalFilename();
            String downloadPath = UUID.randomUUID().toString().replaceAll("-", "") +
                    fileName.substring(fileName.lastIndexOf("."));
            String filePath = "D:/cmfz/cmfz/src/main/webapp/jsp/album/mp3/";
            File dest = new File(filePath + downloadPath);
            file1.transferTo(dest);
            Chapter chapter = new Chapter();
            chapter.setAlbumId(id);
            chapter.setDownloadPath(downloadPath);
            chapter.setPublishDate(new Date());
            chapter.setTitle(ctitle);
            chapter.setDuration(AudioUtil.getDuration(dest));
            long size = file1.getSize();
            chapter.setSize(size / 1024 / 1024 + "MB");
            albumService.insertChapter(chapter);
            map.put("isInsertChapter", true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("isInsertChapter", false);
        }
        return map;
    }

    @RequestMapping("downloadFile")
    public void downloadFile(HttpServletResponse response, String fileName, String title) throws UnsupportedEncodingException {
        if (fileName != null) {
            //设置文件路径
            String realPath = "D:/cmfz/cmfz/src/main/webapp/jsp/album/mp3/";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                String encode = URLEncoder.encode(title, "UTF-8");
                response.addHeader("content-disposition", "attachment;fileName=" + encode);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
