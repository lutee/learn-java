package cn.chenyuxian.learnjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
	
	@Bean
	public RedisTemplate redisTemplate(LettuceConnectionFactory factory) {
		factory.setShareNativeConnection(false);
		RedisTemplate redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}
}
