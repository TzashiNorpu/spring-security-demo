package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("persistent_logins")
@Schema(name = "PersistentLoginsPO对象", description = "")
public class PersistentLoginsPO extends BasePO<PersistentLoginsPO> {

	@Serial
	private static final long serialVersionUID = 1L;

	@TableField("username")
	private String username;

	@TableId(value = "series", type = IdType.ASSIGN_ID)
	private String series;

	@TableField("token")
	private String token;

	@TableField("last_used")
	private LocalDateTime lastUsed;

	@Override
	public Serializable pkVal() {
		return this.series;
	}
}
