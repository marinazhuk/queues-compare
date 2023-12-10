package com.zma.highload.course.message.consumer.app.client;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisClient implements QueueClient {
    public static final String QUEUE_NAME = "queue";
    private final StringRedisTemplate redisTemplate;

    public RedisClient(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getName() {
        return "Redis";
    }

    @Override
    public void get() {
        redisTemplate.opsForList().rightPop(QUEUE_NAME);
    }
}
