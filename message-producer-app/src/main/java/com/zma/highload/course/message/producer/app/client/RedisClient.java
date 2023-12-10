package com.zma.highload.course.message.producer.app.client;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisClient implements QueueClient {
    private final StringRedisTemplate redisTemplate;

    public RedisClient(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getProducerName() {
        return "Redis";
    }

    @Override
    public void put() {
        redisTemplate.opsForList().leftPush("queue", "value");
    }
}
