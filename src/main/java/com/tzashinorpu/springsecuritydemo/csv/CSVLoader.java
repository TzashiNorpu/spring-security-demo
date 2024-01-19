package com.tzashinorpu.springsecuritydemo.csv;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.tzashinorpu.springsecuritydemo.controller.MenuController;
import com.tzashinorpu.springsecuritydemo.pojo.dto.MenuDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
public class CSVLoader {
	@Autowired
	MenuController menuController;

	public void menuLoad() {
		String name = Paths.get("csv", "sysMenu.csv").toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug("sysMenu.csv load error");
		}

		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, MenuDTO.class, new MenuDTODataListener(menuController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void orgLoad() {
		String name = Paths.get("csv", "sysOrg.csv").toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug("sysOrg.csv load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, MenuDTO.class, new MenuDTODataListener(menuController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

}
