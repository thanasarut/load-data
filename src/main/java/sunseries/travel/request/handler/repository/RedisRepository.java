package sunseries.travel.request.handler.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void addData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void addData(String key, String value, long timeout)  {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
    }

    public void addDataExpireInMillisec(String key, String value, long timeout)  {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    public String getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void removeData(String key) {
        redisTemplate.delete(key);
    }

}