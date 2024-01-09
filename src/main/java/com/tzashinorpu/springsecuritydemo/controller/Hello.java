package com.tzashinorpu.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Hello {
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/admin/hello")
	public String admin() {
		return "admin";
	}

	class MyTestUser {
		String name;
		Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}


/*

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public Object login(@RequestBody LoginReq req) {
		String account = req.getAccount();
		String pwd = req.getPwd();
		String code = req.getCode();
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(account, pwd);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication.getPrincipal();
	}

	public class LoginReq {

		private String account;

		private String pwd;

		private String code;

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
*/

}
