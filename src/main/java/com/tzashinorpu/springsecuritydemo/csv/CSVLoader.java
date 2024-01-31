package com.tzashinorpu.springsecuritydemo.csv;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.tzashinorpu.springsecuritydemo.controller.*;
import com.tzashinorpu.springsecuritydemo.pojo.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
@Component
public class CSVLoader {
	@Autowired
	MenuController menuController;
	@Autowired
	OrgController orgController;
	@Autowired
	RoleController roleController;
	@Autowired
	RoleMenuController roleMenuController;
	@Autowired
	OrgRoleController orgRoleController;
	@Autowired
	UserController userController;
	@Autowired
	UserRoleController userRoleController;
	@Autowired
	DeptController deptController;

	public void menuLoad() {
		String csv = "sysMenu.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}

		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, MenuDTO.class, new MenuDTODataListener(menuController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void orgLoad() {
		String csv = "sysOrg.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, OrgDTO.class, new OrgDTODataListener(orgController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void deptLoad() {
		String csv = "sysDept.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, DeptDTO.class, new DeptDTODataListener(deptController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void roleLoad() {
		String csv = "sysRole.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, RoleDTO.class, new RoleDTODataListener(roleController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void roleMenuLoad() {
		String csv = "sysRoleMenu.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, RoleMenuDTO.class, new RoleMenuDTODataListener(roleMenuController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void orgRoleLoad() {
		String csv = "sysOrgRole.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, OrgRoleDTO.class, new OrgRoleDTODataListener(orgRoleController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void userLoad() {
		String csv = "sysUser.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, UserDTO.class, new UserDTODataListener(userController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

	public void userRoleLoad() {
		String csv = "sysUserRole.csv";
		String name = Paths.get("csv", csv).toFile().getPath();
		ClassPathResource classPathResource = new ClassPathResource(name);
		String fileName = null;
		try {
			fileName = classPathResource.getURL().getPath().substring(1);
		} catch (IOException e) {
			log.debug(csv + " load error");
		}
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, UserRoleDTO.class, new UserRoleDTODataListener(userRoleController))
				.excelType(ExcelTypeEnum.CSV)
				.sheet().doRead();
	}

}
