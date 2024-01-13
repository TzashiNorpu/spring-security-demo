package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

@TableName("sys_user")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class UserPO extends BasePO<UserPO>  {
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
    @TableField
    private Integer deptId;
    @TableField
    private Integer orgId;
    // 乐观锁：多读场景，多写悲观锁
    @Version
    private Integer version;
}
