package com.tzashinorpu.springsecuritydemo.MPAutoGen;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 基础测试类
 *
 * @author lanjerry
 * @since 3.5.3
 */
public class BaseGeneratorTest {

	/**
	 * 执行数据库脚本
	 */
	protected static void initDataSource(DataSourceConfig dataSourceConfig) throws SQLException {
		Connection conn = dataSourceConfig.getConn();
		InputStream inputStream = MySQLGeneratorTest.class.getResourceAsStream("/sql/init.sql");
		ScriptRunner scriptRunner = new ScriptRunner(conn);
		scriptRunner.setAutoCommit(true);
		assert inputStream != null;
		scriptRunner.runScript(new InputStreamReader(inputStream));
		conn.close();
	}

	/**
	 * 策略配置
	 */
	protected static StrategyConfig.Builder strategyConfig() {
		StrategyConfig.Builder builder = new StrategyConfig.Builder();
		return builder;

	}

	/**
	 * 全局配置
	 */
	protected static GlobalConfig.Builder globalConfig() {
		GlobalConfig.Builder builder = new GlobalConfig.Builder();
		// 生成目录
		String projectPath = System.getProperty("user.dir");
		builder.outputDir(projectPath);
		builder.author("tzashinorpu");
		builder.enableSwagger();
		// java.util.date
		builder.dateType(DateType.ONLY_DATE);
		return builder;
	}

	/**
	 * 包配置
	 */
	protected static PackageConfig.Builder packageConfig() {
		PackageConfig.Builder builder = new PackageConfig.Builder();
		// 接口名前没有 I
		builder.service("%sService");
		builder.parent("com.tzashinorpu.springsecuritydemo");
//		builder.joinPackage("A");
		return builder;
	}

	/**
	 * 模板配置
	 */
	protected static TemplateConfig.Builder templateConfig() {
		TemplateConfig.Builder builder = new TemplateConfig.Builder();
		return builder;
	}

	/**
	 * 注入配置
	 */
	protected static InjectionConfig.Builder injectionConfig() {
		// 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
		return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
			System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
		});
	}
}
