package com.tzashinorpu.springsecuritydemo.config.mybatis;

import com.tzashinorpu.springsecuritydemo.config.security.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginSuccessListener implements ApplicationListener<LoginSuccessEvent> {
	@Override
	public void onApplicationEvent(LoginSuccessEvent event) {
		log.debug("event from " + event.getSource() + " happened!");
		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("current user is :" + userName);
	}
}
