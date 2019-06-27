package com.jester.music.service.impl;

import com.jester.music.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RedisServiceImpl
 *
 * @author Jester
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 12:42
 * @version version 1.0.0
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    RedisServiceImpl(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long leftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long rightPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long leftPushAll(String key, Object... value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    @Override
    public Long rightPushAll(String key, Object... value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    @Override
    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Object leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public List getAll(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public Long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Object get(final String key) {
        Object result;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    @Override
    public boolean hasKey(final String key) {
        return  redisTemplate.hasKey(key);
    }

    @Override
    public boolean updateOutTime(String key, Object value, long time) {
        try {
            this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void set(String key, String value, long timeOut) {
        this.redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
    }
}
