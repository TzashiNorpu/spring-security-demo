package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sys_role")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class RolePO extends BasePO<RolePO> {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String nameZh;
    @Version
    private Integer version;
}