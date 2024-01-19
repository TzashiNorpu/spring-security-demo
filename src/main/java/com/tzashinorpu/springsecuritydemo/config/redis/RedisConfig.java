package com.tzashinorpu.springsecuritydemo.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.session.MapSession;
import org.springframework.session.config.SessionRepositoryCustomizer;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.RedisSessionMapper;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;

import java.util.Map;
import java.util.function.BiFunction;

@Configuration(proxyBeanMethods = false)
@EnableRedisIndexedHttpSession(redisNamespace = "spring.tzashinorpu")
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

	@Bean
	SessionRepositoryCustomizer<RedisIndexedSessionRepository> redisSessionRepositoryCustomizer() {
		return (redisSessionRepository) -> redisSessionRepository.setRedisSessionMapper(
				new SafeRedisSessionMapper(redisSessionRepository.getSessionRedisOperations()));
	}

	static class SafeRedisSessionMapper implements BiFunction<String, Map<String, Object>, MapSession> {

		private final RedisSessionMapper delegate = new RedisSessionMapper();

		private final RedisOperations<String, Object> redisOperations;

		SafeRedisSessionMapper(RedisOperations<String, Object> redisOperations) {
			this.redisOperations = redisOperations;
		}

		@Override
		public MapSession apply(String sessionId, Map<String, Object> map) {
			try {
				return this.delegate.apply(sessionId, map);
			} catch (IllegalStateException ex) {
				// if you use a different redis namespace, change the key accordingly
				this.redisOperations.delete("spring:session:sessions:" + sessionId); // we do not invoke RedisIndexedSessionRepository#deleteById to avoid an infinite loop because the method also invokes this mapper
				return null;
			}
		}

	}
}
