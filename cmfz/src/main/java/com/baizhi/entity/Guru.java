package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guru")
public class Guru {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String id;
    private String dharma;
    private Integer status;
    private String headImg;

}
