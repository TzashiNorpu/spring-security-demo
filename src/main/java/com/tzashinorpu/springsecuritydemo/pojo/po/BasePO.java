package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class BasePO<T extends Model<?>> extends Model<T> {
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
	@TableField(fill = FieldFill.INSERT)
	private Long createdBy;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateBy;
		// 逻辑删除标识
	@TableLogic
	private boolean deleted;
}
