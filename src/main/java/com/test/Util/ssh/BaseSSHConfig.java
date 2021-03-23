package com.test.Util.ssh;

public class BaseSSHConfig {
private String sshHost;
private String sshUser;
private String sshKeyFilepath;
private String passPhrase;
private String configPath;
private int port;

public BaseSSHConfig() {
}

public BaseSSHConfig(String sshHost, String sshUser, String sshKeyFilepath) {
this(sshHost, sshUser, sshKeyFilepath, (String)null, 22);
}

public BaseSSHConfig(String sshHost, String sshUser, String sshKeyFilepath, int port) {
this(sshHost, sshUser, sshKeyFilepath, (String)null, port);
}

public BaseSSHConfig(String sshHost, String sshUser, String sshKeyFilepath, String passPhrase, int port) {
this.sshHost = sshHost;
this.sshUser = sshUser;
this.sshKeyFilepath = sshKeyFilepath;
this.passPhrase = passPhrase;
this.port = port;
}

public BaseSSHConfig(String sshHost, String sshUser, String sshKeyFilepath, String passPhrase, String configPath) {
this.sshHost = sshHost;
this.sshUser = sshUser;
this.sshKeyFilepath = sshKeyFilepath;
this.passPhrase = passPhrase;
this.configPath = configPath;
}

public int getPort() {
return this.port;
}

public void setPort(int port) {
this.port = port;
}

public String getSshHost() {
return this.sshHost;
}

public void setSshHost(String host) {
this.sshHost = host;
}

public String getSshKeyFilepath() {
return this.sshKeyFilepath;
}

public void setSshKeyFilepath(String sshKeyFile) {
this.sshKeyFilepath = sshKeyFile;
}

public String getSshUser() {
return this.sshUser;
}

public void setSshUser(String user) {
this.sshUser = user;
}

public String getPassPhrase() {
return this.passPhrase;
}

public void setPassPhrase(String passPhrase) {
this.passPhrase = passPhrase;
}

public String getConfigPath() {
return this.configPath;
}

public void setConfigPath(String configPath) {
this.configPath = configPath;
}

public boolean equals(Object o) {
if (this == o) {
return true;
} else if (!(o instanceof BaseSSHConfig)) {
return false;
} else {
BaseSSHConfig that = (BaseSSHConfig)o;
if (this.sshHost != null) {
if (this.sshHost.equals(that.sshHost)) {
return this.sshUser != null ? this.sshUser.equals(that.sshUser) : that.sshUser == null;
}
} else if (that.sshHost == null) {
return this.sshUser != null ? this.sshUser.equals(that.sshUser) : that.sshUser == null;
}

return false;
}
}

public int hashCode() {
int result = this.sshHost != null ? this.sshHost.hashCode() : 0;
result = 31 * result + (this.sshUser != null ? this.sshUser.hashCode() : 0);
return result;
}

public String toString() {
return "BaseSSHConfig{sshHost='" + this.sshHost + '\'' + ", sshUser='" + this.sshUser + '\'' + ", sshKeyFilepath='" + this.sshKeyFilepath + '\'' + ", passPhrase='" + this.passPhrase + '\'' + '}';
}
}
