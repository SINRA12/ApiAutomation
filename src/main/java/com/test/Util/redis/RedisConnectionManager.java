package com.test.Util.redis;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionManager {
final JedisPoolConfig poolConfig;
private Map<RedisConf, RedisClient> redisPoolMap;

private RedisConnectionManager() {
this.poolConfig = this.buildPoolConfig();
this.redisPoolMap = new HashMap();
}

public static RedisConnectionManager getInstance() {
return RedisConnectionManager.InstanceHolder.instance_;
}

public RedisClient getRedisClient(RedisConf conf) {
RedisClient obj = (RedisClient)this.redisPoolMap.get(conf);
if (obj == null) {
synchronized(this.redisPoolMap) {
obj = (RedisClient)this.redisPoolMap.get(conf);
if (obj == null) {
obj = this.init(conf);
this.redisPoolMap.put(conf, obj);
}
}
}

return obj;
}

private RedisClient init(RedisConf conf) {
return new RedisClient(new JedisPool(this.poolConfig, conf.getHostName(), conf.getPort()));
}

private JedisPoolConfig buildPoolConfig() {
JedisPoolConfig poolConfig = new JedisPoolConfig();
poolConfig.setMaxTotal(128);
poolConfig.setMaxIdle(128);
poolConfig.setMinIdle(16);
poolConfig.setTestOnBorrow(true);
poolConfig.setTestOnReturn(true);
poolConfig.setTestWhileIdle(true);
poolConfig.setMinEvictableIdleTimeMillis(60000L);
poolConfig.setTimeBetweenEvictionRunsMillis(30000L);
poolConfig.setNumTestsPerEvictionRun(3);
poolConfig.setBlockWhenExhausted(true);
return poolConfig;
}

public Map<RedisConf, RedisClient> getRedisPoolMap() {
return Collections.unmodifiableMap(this.redisPoolMap);
}

private static class InstanceHolder {
static final RedisConnectionManager instance_ = new RedisConnectionManager();

private InstanceHolder() {
}
}
}