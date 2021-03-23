package com.test.Util.ssh;

public class SshDetails extends BaseSSHConfig {
private String remoteHost;
private String dbUserName;
private String dbPassword;
private int localPort;
private int remotePort;
private int sshPort;

public SshDetails() {
}

public int getSshPort() {
return this.sshPort;
}

public void setSshPort(int sshPort) {
this.sshPort = sshPort;
}

public String getRemoteHost() {
return this.remoteHost;
}

public void setRemoteHost(String rmHost) {
this.remoteHost = rmHost;
}

public String getDbUserName() {
return this.dbUserName;
}

public void setDbUserName(String userName) {
this.dbUserName = userName;
}

public String getDbPassword() {
return this.dbPassword;
}

public void setDbPassword(String password) {
this.dbPassword = password;
}

public int getRemotePort() {
return this.remotePort;
}

public void setRemotePort(int rmPort) {
this.remotePort = rmPort;
}

public int getLocalPort() {
return this.localPort;
}

public void setLocalPort(int port) {
this.localPort = port;
}
}
