package com.jixianit.redis;

import com.jixianit.util.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class RedisStreamConsumer {
    public void send() {
        RedisConnectionUtil rcu = new RedisConnectionUtil() ;
        Jedis jedis = rcu.getConnection() ;
    }
}
