package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.captcha.VerifyCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class VerifyCodeController {
	@GetMapping("/verify-code")
	public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		String text = vc.getText();
		HttpSession session = req.getSession();
		session.setAttribute("verify_code", text);
		VerifyCode.output(image, resp.getOutputStream());
	}
}
