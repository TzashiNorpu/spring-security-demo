package com.tzashinorpu.springsecuritydemo.DaoTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.tzashinorpu.springsecuritydemo.controller.MenuController;
import com.tzashinorpu.springsecuritydemo.pojo.dto.MenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootTest
class BatchInsertTest {
	@Autowired
	MenuController menuController;

	@Test
	void CSVAdd() throws IOException {
		// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
		// 写法3：
		String name = Paths.get("csv", "sysMenu.csv").toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = classPathResource.getURL().getPath().substring(1);
		/*System.out.println(fileName);
		Path path = Paths.get(fileName);
		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			lines.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, MenuDTO.class, new DemoDataListener(menuController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}
}