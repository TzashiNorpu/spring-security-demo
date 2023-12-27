package com.tzashinorpu.springsecuritydemo.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;

@Configuration(proxyBeanMethods = false)
@EnableRedisIndexedHttpSession(redisNamespace="spring.tzashinorpu")
public class RedisConfig {
	@Value("${server.servlet.session.timeout}")
	private int time_out;
	@Value("${spring.data.redis.port}")
	private int port;
	@Value("${spring.data.redis.host}")
	private String host;
	@Value("${spring.data.redis.password}")
	private String passwd;
	@Bean
	public LettuceConnectionFactory connectionFactory() {
		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().build();
		RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(host, port);
		serverConfig.setPassword(RedisPassword.of(passwd));
		return new LettuceConnectionFactory(serverConfig, clientConfig);
	}
}
