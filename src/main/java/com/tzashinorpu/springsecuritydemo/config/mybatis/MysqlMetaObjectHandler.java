package com.tzashinorpu.springsecuritydemo.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tzashinorpu.springsecuritydemo.config.security.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

/**
 * 测试，自定义元对象字段填充控制器，实现公共字段自动写入
 */
@Slf4j
public class MysqlMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 测试 user 表 name 字段为空自动填充
	 */
	// 默认每次都调：没有 createTime 属性的实体对象不用调这个 insertFill
	@Override
	public void insertFill(MetaObject metaObject) {
		System.out.println("*************************");
		System.out.println("insert of mysql fill");
		System.out.println("*************************");
		// create_time 是实体对象属性名
		if (metaObject.hasGetter("createTime") && metaObject.getValue("createTime") == null)
			this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());

		// accountNonExpired
		if (metaObject.hasGetter("accountNonExpired") && metaObject.getValue("accountNonExpired") == null)
			this.strictInsertFill(metaObject, "accountNonExpired", Boolean.class, false);
		// accountNonLocked
		if (metaObject.hasGetter("accountNonLocked") && metaObject.getValue("accountNonLocked") == null)
			this.strictInsertFill(metaObject, "accountNonLocked", Boolean.class, false);
		// credentialsNonExpired
		if (metaObject.hasGetter("credentialsNonExpired") && metaObject.getValue("credentialsNonExpired") == null)
			this.strictInsertFill(metaObject, "credentialsNonExpired", Boolean.class, false);
		// enabled
		if (metaObject.hasGetter("enabled") && metaObject.getValue("enabled") == null)
			this.strictInsertFill(metaObject, "enabled", Boolean.class, true);

		if (metaObject.hasGetter("createBy") && metaObject.getValue("createBy") == null) {
			Object principal = null;
			try {
				principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				log.debug("SecurityContextHolder 获取用户失败");
			}
			Long userId = 999999L;
			if (principal instanceof CustomUserDetail)
				userId = ((CustomUserDetail) principal).getId();
			this.strictInsertFill(metaObject, "createBy", Long.class, userId);
		}

		/*// 测试下划线
		Object createDatetime = this.getFieldValByName("createDatetime", metaObject);
		System.out.println("createDatetime=" + createDatetime);
		if (createDatetime == null) {
			//测试实体没有的字段，配置在公共填充，不应该set到实体里面
			this.strictInsertFill(metaObject, "createDatetime1", LocalDateTime.class, LocalDateTime.now())
					.strictInsertFill(metaObject, "createDatetime", LocalDateTime.class, LocalDateTime.now());
		}*/
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		System.out.println("*************************");
		System.out.println("update of mysql fill");
		System.out.println("*************************");
		if (metaObject.hasGetter("updateTime") && metaObject.getValue("updateTime") == null)
			this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

		if (metaObject.hasGetter("updateBy") && metaObject.getValue("updateBy") == null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Long userId;
			if (principal instanceof CustomUserDetail)
				userId = ((CustomUserDetail) principal).getId();
			else
				userId = (Long) metaObject.getValue("id");
			this.strictInsertFill(metaObject, "updateBy", Long.class, userId);
		}
	}
}
