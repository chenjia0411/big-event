package com.heima;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: chenjia
 * @Date: 2023/12/8 - 12 - 08 - 14:40
 * @Description: com.heima
 * @version: 1.0*/


@SpringBootTest //如果在测试类上添加了这个注解，那么将来单元测试方法执行之前，会先初始化Spring容器
                //这样就可以拿到容器的对象
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet(){
        //往redies中存储一个键值对  stringRedisTemplate
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("chenjia","chenjia",1, TimeUnit.HOURS);
    }
}
