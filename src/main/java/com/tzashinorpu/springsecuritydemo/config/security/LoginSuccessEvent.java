package com.tzashinorpu.springsecuritydemo.config.security;

import org.springframework.context.ApplicationEvent;

public class LoginSuccessEvent extends ApplicationEvent {
	public LoginSuccessEvent(Object source) {
		super(source);
	}
}
