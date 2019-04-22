package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.AlbumMapper;
import com.baizhi.dao.MenuMapper;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.Album;
import com.baizhi.entity.Menu;
import com.baizhi.service.BannerService;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {


    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void contextLoads() {
        List<Menu> menus = menuMapper.queryAllMenu();
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser() {
        System.out.println(userService.selectWithMap(0));
//        System.out.println(userService.selectAllUser(7));
//        System.out.println(userMapper.selectWithMap(0));
    }

    @Test
    public void Test01() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("id = " + id);
    }

    @Test
    public void TestPOI1() {
        // 创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("Test01");
        // 创建行
        HSSFRow row = sheet.createRow(0);
        String[] title = {"编号", "姓名", "生日"};
        // 声明单元格
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++) {
            // 创建单元格
            cell = row.createCell(i);
            // 设置单元格的值
            cell.setCellValue(title[i]);
        }
        // 处理日期格式
        // 创建样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        // 日期格式对象
        DataFormat dataFormat = workbook.createDataFormat();
        // 设置日期格式
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));
        // 处理数据行
        for (int j = 0; j < 10; j++) {
            // 创建行
            row = sheet.createRow(j + 1);
            // 创建单元格，设置单元格值
            row.createCell(0).setCellValue(j);
            row.createCell(1).setCellValue("张三" + j);
            //设置出生年月格式
            cell = row.createCell(2);
            cell.setCellValue(new Date());
            cell.setCellStyle(cellStyle);
        }

        try {
            workbook.write(new File("D:\\Test.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestEasyPOI() {
        List<Album> albums = albumMapper.selectAllAlbum();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑详情", "专辑"),
                Album.class, albums);
        try {
            workbook.write(new FileOutputStream(new File("D:/easypoi.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*  @Test
    public void testPOI() {
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建字体
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("楷体");
        font.setItalic(true);
        font.setColor(Font.COLOR_RED);
        //创建日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //创建样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //创建日期格式的样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
        //创建工作表
        Sheet sheet = workbook.createSheet("user");
        //第一个参数给那个列设置宽度  下标   第二个参数  宽度设置为多少  需要乘以256
        sheet.setColumnWidth(3,20*256);
        //编号  名字   生日
        String[] strings = {"编号","名字","年龄","生日"};
        //创建行  参数第几行  下标
        Row row = sheet.createRow(0);
        for (int i = 0; i < strings.length; i++) {
            //创建单元格
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            //单元格赋值
            cell.setCellValue(strings[i]);
        }
        for (int i = 0; i < 10; i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue("张三"+i);
            row1.createCell(1).setCellValue(i);
            row1.createCell(2).setCellValue(i);
            Cell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(new Date());
        }
        try {
            workbook.write(new FileOutputStream(new File("D:/bxy.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
