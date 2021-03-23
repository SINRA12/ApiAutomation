package com.test.Util.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ConfigRepository;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.OpenSSHConfig;
import com.jcraft.jsch.Session;
import com.test.customReporter.CustomLogger;
import com.test.Exception.InvalidConfigurationException;
import com.test.Exception.SSHExecutionException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSHConnectionBase {
private static final Logger LOGGER = LoggerFactory.getLogger(SSHConnectionBase.class);
private BaseSSHConfig config;
private Session session = null;

public SSHConnectionBase(BaseSSHConfig config) {
this.config = config;
this.init();
}

private void init() {
JSch jsch = new JSch();

try {
if (null != this.config.getPassPhrase() && !this.config.getPassPhrase().isEmpty()) {
jsch.addIdentity(this.config.getSshKeyFilepath(), this.config.getPassPhrase());
} else {
jsch.addIdentity(this.config.getSshKeyFilepath());
}

if (null != this.config.getConfigPath() && !this.config.getConfigPath().isEmpty()) {
ConfigRepository rep = OpenSSHConfig.parse(this.config.getConfigPath());
jsch.setConfigRepository(rep);
}

this.session = jsch.getSession(this.config.getSshUser(), this.config.getSshHost(), this.config.getPort());
this.session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
Properties config = new Properties();
config.put("StrictHostKeyChecking", "no");
config.put("ConnectionAttempts", "3");
this.session.setConfig(config);
this.session.connect();
} catch (Exception var3) {
CustomLogger.logFail("Error creating remote ssh connection" + var3);
throw new InvalidConfigurationException("Error creating remote ssh connection" + var3);
}
}

public synchronized String invokeCommand(String command) {
return this.invokeCommand(command, 2147483647);
}

public synchronized String invokeCommand(String command, Integer connTimeoutInSec) {
StringBuilder sb = new StringBuilder();
Channel channel = null;

try {
if (null == this.session || !this.session.isConnected()) {
this.init();
}

CustomLogger.logPass("Executing SSH Command :::::::::: " + command);
channel = this.session.openChannel("exec");
((ChannelExec)channel).setCommand(command);
((ChannelExec)channel).setPty(true);
InputStream in = channel.getInputStream();
channel.connect(connTimeoutInSec);
BufferedReader reader = new BufferedReader(new InputStreamReader(in));

while(true) {
String line;
if ((line = reader.readLine()) == null) {
channel.disconnect();
break;
}

sb.append(line).append(System.getProperty("line.separator"));
}
} catch (Exception var8) {
channel.disconnect();
throw new SSHExecutionException("Error durring SSH command execution. Command: " + var8);
}

CustomLogger.logPass("SSH Command Output :::::::::: " + sb.toString());
return sb.toString();
}

public void closeSSHConnection() {
if (null != this.session && this.session.isConnected()) {
LOGGER.info("Closing SSH Connection");
this.session.disconnect();
}

}
}