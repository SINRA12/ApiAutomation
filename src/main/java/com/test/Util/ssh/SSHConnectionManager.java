package com.test.Util.ssh;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SSHConnectionManager {
private Map<BaseSSHConfig, SSHConnectionBase> connectionMap;

private SSHConnectionManager() {
this.connectionMap = new HashMap();
}

public static SSHConnectionManager getInstance() {
return SSHConnectionManager.InstanceHolder.instance_;
}

public SSHConnectionBase getSSHConnection(BaseSSHConfig conf) {
SSHConnectionBase helper = (SSHConnectionBase)this.connectionMap.get(conf);
if (helper == null) {
synchronized(this.connectionMap) {
helper = (SSHConnectionBase)this.connectionMap.get(conf);
if (helper == null) {
helper = new SSHConnectionBase(conf);
this.connectionMap.put(conf, helper);
}
}
}

return helper;
}

public Map<BaseSSHConfig, SSHConnectionBase> getConnectionMap() {
return Collections.unmodifiableMap(this.connectionMap);
}

private static class InstanceHolder {
static final SSHConnectionManager instance_ = new SSHConnectionManager();

private InstanceHolder() {
}
}
}
