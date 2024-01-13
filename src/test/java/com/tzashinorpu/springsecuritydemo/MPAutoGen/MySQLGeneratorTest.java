package com.tzashinorpu.springsecuritydemo.MPAutoGen;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MySQL 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */
public class MySQLGeneratorTest extends BaseGeneratorTest {

	/**
	 * 数据源配置
	 */
	private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
			.Builder("jdbc:mysql://10.168.1.134:33061/security-demo?serverTimezone=Asia/Shanghai", "root", "yzs74520")
			.schema("security-demo")
			/*.build()*/;

	/*@Test
	public void testSimple() {
		AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
		generator.strategy(strategyConfig().build());
		generator.global(globalConfig().build());
		generator.execute();
	}*/

	// 处理 all 情况
	protected static List<String> getTables(String tables) {
		return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
	}

	@Test
	public void myTest() {
		FastAutoGenerator.create(DATA_SOURCE_CONFIG)
				// 全局配置
				.globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")))
				// 包配置
				.packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
				// 策略配置
				.strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
						.controllerBuilder().enableRestStyle().enableHyphenStyle()
						.entityBuilder().enableLombok().addTableFills(
								new Column("create_time", FieldFill.INSERT)
						).build())
				/*
						模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
					 .templateEngine(new BeetlTemplateEngine())
					 .templateEngine(new FreemarkerTemplateEngine())
				 */
				.execute();
	}
}
