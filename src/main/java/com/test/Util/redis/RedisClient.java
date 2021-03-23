package com.test.Util.redis;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClient {
private JedisPool jedisPool;

protected RedisClient(JedisPool jedisPool) {
this.jedisPool = jedisPool;
}

public JedisPool getJedisPool() {
return this.jedisPool;
}

public void closeConnection() {
if (!this.jedisPool.isClosed()) {
this.jedisPool.close();
}

}

public String getKey(String key) {
Jedis jedis = this.jedisPool.getResource();

String var3;
try {
var3 = jedis.get(key);
} finally {
if (jedis.isConnected()) {
jedis.close();
}

}

return var3;
}

public void setKey(String key, String value) {
Jedis jedis = this.jedisPool.getResource();

try {
jedis.set(key, value);
} finally {
if (jedis.isConnected()) {
jedis.close();
}

}

}

public void flushAll() {
Jedis jedis = this.jedisPool.getResource();

try {
jedis.flushAll();
} finally {
if (jedis.isConnected()) {
jedis.close();
}

}

}

public Long deleteKeys(String[] key) {
Jedis jedis = this.jedisPool.getResource();

Long var3;
try {
var3 = jedis.del(key);
} finally {
if (jedis.isConnected()) {
jedis.close();
}

}

return var3;
}

public Set<String> getAllKeys(String pattern) {
Jedis jedis = this.jedisPool.getResource();

Set var3;
try {
var3 = jedis.keys(pattern);
} finally {
if (jedis.isConnected()) {
jedis.close();
}

}

return var3;
}

public Map<String, String> hgetAll(String key) {
Jedis jedis = this.jedisPool.getResource();

Map var3;
try {
var3 = jedis.hgetAll(key);
} finally {
if (jedis.isConnected()) {
jedis.close();
}

}

return var3;
}

public void hmset(String key, Map<String, String> obj) {
Jedis jedis = this.jedisPool.getResource();

try {
jedis.hmset(key, obj);
} finally {
if (jedis.isConnected()) {
jedis.close();
}

}

}
}