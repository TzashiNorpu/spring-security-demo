package com.tzashinorpu.springsecuritydemo.config.redis;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tzashinorpu.springsecuritydemo.mixin.LongMixin;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;

import java.util.List;


@Configuration
public class RedisSerializerConfig implements BeanClassLoaderAware {

	private ClassLoader loader;

	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer(/*ObjectMapper objectMapper*/) {
//		return new GenericJackson2JsonRedisSerializer(objectMapper);
		return new GenericJackson2JsonRedisSerializer(objectMapper());
	}

	/**
	 * Customized {@link ObjectMapper} to add mix-in for class that doesn't have default
	 * constructors
	 * @return the {@link ObjectMapper} to use
	 */
	private ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		List<Module> modules = SecurityJackson2Modules.getModules(this.loader);
		mapper.registerModules(modules);
		mapper.addMixIn(Long.class, LongMixin.class);
		return mapper;
	}

	/*
	 * @see
	 * org.springframework.beans.factory.BeanClassLoaderAware#setBeanClassLoader(java.lang
	 * .ClassLoader)
	 */
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.loader = classLoader;
	}

}
