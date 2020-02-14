package space.nyuki.qclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import space.nyuki.qclient.pojo.QuestionnaireEntity;

@Configuration
public class RedisConfig {
//    @Bean
//    public RedisTemplate<String, String> myObjectRedisTemplate(RedisConnectionFactory factory){
//        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        redisTemplate.afterPropertiesSet();
//        redisTemplate.setKeySerializer(RedisSerializer.string());
//        redisTemplate.setValueSerializer(RedisSerializer.string());
//        return redisTemplate;
//    }
}