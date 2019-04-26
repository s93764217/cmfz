package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
public class Album {
    @Id
    private String id;
    @Excel(name = "专辑名称", needMerge = true)
    private String title;
    private Integer amount;
    @Excel(name = "封面", width = 40, needMerge = true, type = 2)
    private String imgPath;
    private Integer score;
    private String author;
    private String announcer;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String introduction;

    @Transient
    @ExcelCollection(name = "章节详情")
    private List<Chapter> children;

}
