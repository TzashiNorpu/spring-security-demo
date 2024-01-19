package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "BasePO 对象", description = "PO 基础对象")
public class BasePO<T extends Model<?>> extends Model<T> {
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	@TableField(fill = FieldFill.INSERT)
	private Long createBy;
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateBy;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 逻辑删除标识
	@TableLogic
	private boolean deleted;
}
