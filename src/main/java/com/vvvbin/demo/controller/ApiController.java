package com.vvvbin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ApiController {
    @Autowired
    private RedisTemplate redisTemplate;

    //添加hash,需要hash名和存储的键值对Map
    public void setHash(String hashName, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(hashName, map);
    }

    //Springboot的启动器main方法上需要加上@EnableCaching开启缓存，使用了@Cacheable注解后，缓存的值将被存入redis数据库中
    //缓存名可以为RedisConfig中自定义的缓存名，键生成器为RedisConig中自定义的键生成器，也可以自己自定义缓存key名

    //从redis中获取map
    @ResponseBody
    @GetMapping("/getKey")
    public Object getHash(String key) {
        // Map o = redisTemplate.opsForHash().entries("20220424-test");
        Object o1 = redisTemplate.opsForHash().get("20220424-test", key);
        System.out.println(o1);
        return o1;
    }

}
