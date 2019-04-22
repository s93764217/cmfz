package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapter")
public class Chapter {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @Excel(name = "章节名称", width = 30)
    private String title;
    @Excel(name = "章节大小")
    private String size;
    @Excel(name = "章节时长")
    private String duration;
    @Excel(name = "下载路径", width = 50)
    private String downloadPath;
    @Excel(name = "发行日期", format = "yyyy年MM月dd日HH时mm分ss秒", width = 50)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date publishDate;
    private String albumId;
}
