package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.ibatis.type.JdbcType;

@TableName("sys_user")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS)
public class UserPO extends BasePO{
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    @TableField(jdbcType = JdbcType.BOOLEAN,fill=FieldFill.INSERT)
    private boolean accountNonExpired;
    @TableField(jdbcType = JdbcType.BOOLEAN)
    private boolean accountNonLocked;
    @TableField(jdbcType = JdbcType.BOOLEAN)
    private boolean credentialsNonExpired;
    @TableField(jdbcType = JdbcType.BOOLEAN)
    private boolean enabled;

}
