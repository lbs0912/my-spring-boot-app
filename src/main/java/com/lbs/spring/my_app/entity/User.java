package com.lbs.spring.my_app.entity;


import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String createTime;
    private String updateTime;
    private Integer version;
    private Integer deleted;
    private String remark;
}
