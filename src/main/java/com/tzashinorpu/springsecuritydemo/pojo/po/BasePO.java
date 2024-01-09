package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;

import java.util.Date;

public class BasePO {
	@TableField(fill = FieldFill.INSERT)
	private Date create_time;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date update_time;
	@TableField(fill = FieldFill.INSERT)
	private String created_by;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String update_by;
	// 乐观锁
	@Version
	private Integer version;

}
