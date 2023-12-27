package com.tzashinorpu.springsecuritydemo.config.redis;

import com.tzashinorpu.springsecuritydemo.config.redis.RedisConfig;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Initializer extends AbstractHttpSessionApplicationInitializer {

	public Initializer() {
		super(RedisConfig.class);
	}

}
