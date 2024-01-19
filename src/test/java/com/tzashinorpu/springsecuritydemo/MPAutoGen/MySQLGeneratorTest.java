package com.tzashinorpu.springsecuritydemo.MPAutoGen;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.tzashinorpu.springsecuritydemo.pojo.po.BasePO;
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
		String pkgName = "com.tzashinorpu.springsecuritydemo";
		String outputDir = "D://tmp";
		FastAutoGenerator.create(DATA_SOURCE_CONFIG)
				// 全局配置
				.globalConfig(
						builder -> {
							builder.author("tzashinorpu") // 设置作者
									.enableSwagger() // 开启 swagger 模式
									.outputDir(outputDir) // 指定输出目录
							;
						}
				)
				// 包配置
				.packageConfig(
						builder -> {

							builder.parent(pkgName) // 设置父包名
									.entity("pojo.po")  // Entity 包名
//								.moduleName("system") // 设置父包模块名
									// resources\mapper
									.pathInfo(Collections.singletonMap(OutputFile.xml, outputDir)
									) // 设置mapperXml生成路径
							;
						}
				)
				// 策略配置
				.strategyConfig(
						builder -> {
							builder.enableCapitalMode()
									/*.enableSkipView()*/
//									.disableSqlFilter()
//									.likeTable(new LikeTable("sys_dept"))
									.entityBuilder()
									.superClass(BasePO.class)  // 设置父类
									.enableChainModel()
									.enableLombok()
									.enableRemoveIsPrefix()
									.enableTableFieldAnnotation()
									.enableActiveRecord()
									.versionColumnName("version")
									//.versionPropertyName("version")
									.logicDeleteColumnName("deleted")
									//.logicDeletePropertyName("deleteFlag")
									.naming(NamingStrategy.underline_to_camel)
									.columnNaming(NamingStrategy.underline_to_camel)
									.addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time", "deleted")
//									.addIgnoreColumns("age")
/*									.addTableFills(new Column("create_time", FieldFill.INSERT_UPDATE))
									.addTableFills(new Column("updateTime", FieldFill.INSERT_UPDATE))
									.addTableFills(new Column("created_by", FieldFill.INSERT_UPDATE))
									.addTableFills(new Column("update_by", FieldFill.INSERT_UPDATE))*/
									.idType(IdType.ASSIGN_ID)
									.formatFileName("%sPO")
									.enableFileOverride()
									.serviceBuilder()
									.formatServiceFileName("%sService")
									.formatServiceImplFileName("%sServiceImpl")
									.enableFileOverride()
							;
						}
				)
				.execute();
	}
}
