package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAll")
    public @ResponseBody
    Map selectAll(int rows, int page) {
        return bannerService.selectAll(page, rows);
    }

    @RequestMapping("insert")
    public @ResponseBody
    Map insert(String title, MultipartFile file1) {
        Map map = null;
        try {
            map = new HashMap();
            String fileName = file1.getOriginalFilename();
            String filePath = "D:/cmfz/cmfz/src/main/webapp/jsp/banner/img/";
            File dest = new File(filePath + fileName);
            file1.transferTo(dest);
            Banner banner = new Banner();
            banner.setAddTime(new Date());
            banner.setImgPath(fileName);
            banner.setStatus(0);
            banner.setTitle(title);
            bannerService.insert(banner);
            map.put("isInsert", true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("isInsert", false);
        }
        return map;
    }

    @RequestMapping("updateStatus")
    public void updateStatus(Banner banner) {
        bannerService.updateStatus(banner);
    }

    @RequestMapping("delete")
    public void delete(int id) {
        bannerService.delete(id);
    }
}
