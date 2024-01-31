package com.tzashinorpu.springsecuritydemo.csv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CSVLoaderTest {
	@Autowired
	CSVLoader loader;


	@Test
	void menuLoad() {
		loader.menuLoad();
	}


	@Test
	void orgLoad() {
		loader.orgLoad();
	}

	@Test
	void deptLoad() {
		loader.deptLoad();
	}

	@Test
	void userLoad() {
		loader.userLoad();
	}

	@Test
	void roleLoad() {
		loader.roleLoad();
	}

	@Test
	void orgRoleLoad() {
		loader.orgRoleLoad();
	}

	@Test
	void roleMenuLoad() {
		loader.roleMenuLoad();
	}

	@Test
	void userRoleLoad() {
		loader.userRoleLoad();
	}

}