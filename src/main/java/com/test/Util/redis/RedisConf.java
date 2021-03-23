package com.test.Util.redis;

public class RedisConf {
private String hostName;
private Integer port;
private String name;

public RedisConf() {
}

public String getHostName() {
return this.hostName;
}

public void setHostName(String hostName) {
this.hostName = hostName;
}

public Integer getPort() {
return this.port;
}

public void setPort(Integer port) {
this.port = port;
}

public String getName() {
return this.name;
}

public void setName(String name) {
this.name = name;
}

public boolean equals(Object o) {
if (this == o) {
return true;
} else if (!(o instanceof RedisConf)) {
return false;
} else {
RedisConf redisConf = (RedisConf)o;
return this.name != null ? this.name.equals(redisConf.name) : redisConf.name == null;
}
}

public int hashCode() {
return this.name != null ? this.name.hashCode() : 0;
}
}