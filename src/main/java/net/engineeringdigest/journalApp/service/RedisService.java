package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> weatherResponseClass) {
        log.debug("Getting value for key: {}", key);
        Object o = redisTemplate.opsForValue().get(key);

        if (o == null) {
            log.warn("No value found in Redis for key: {}", key);
            return null;
        }

        log.debug("Value from Redis: {}", o);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(o.toString(), weatherResponseClass );
        } catch (JsonProcessingException e) {
            //throw new RuntimeException(e);
            log.error("Failed to parse value from Redis for key {}: {}", key, e.getMessage(), e);
            return null;
        }
    }

    public void set(String key, Object o, Long expiryInSeconds) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, json, expiryInSeconds, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Failed to serialize object for key {}: {}", key, e.getMessage(), e);
        }
    }
}
