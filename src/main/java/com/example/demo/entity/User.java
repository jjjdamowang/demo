package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")

public class User implements Serializable{
    @TableId(type = IdType.AUTO)
    @JsonProperty("id")
    private Long id;

    @TableField("name")  // 改成小写 name，与数据库字段匹配
    @JsonProperty("name")
    private String name;

    @TableField("age")   // age 保持不变（假设数据库也是小写 age）
    @JsonProperty("age")
    private Integer age;
}