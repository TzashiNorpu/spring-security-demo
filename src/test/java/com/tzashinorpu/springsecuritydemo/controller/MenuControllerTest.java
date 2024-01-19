package com.tzashinorpu.springsecuritydemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class MenuControllerTest {
	@Autowired
	MenuController menuController;

	@Test
	void addMenu() {
//		MenuDTO menuDto=new MenuDTO();
		Path path = Paths.get("sysMenu.csv");
		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			lines.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
//		menuController.addMenu(menuDto);

	}
}