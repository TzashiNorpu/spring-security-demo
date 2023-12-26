package com.tzashinorpu.springsecuritydemo.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Initializer extends AbstractHttpSessionApplicationInitializer {

	public Initializer() {
		super(RedisConfig.class);
	}

}
