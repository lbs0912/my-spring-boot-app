package com.lbs.spring.my_app.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("mp_user")
public class User {
    private Long id;
    @TableField("name")
    private String realName;
    private Integer age;
    private String email;
}
