package com.test.Util.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSHConnection {
private static final Logger LOGGER = LoggerFactory.getLogger(SSHConnection.class);
public Properties properties;
private Connection connection = null;
private Session session = null;

public SSHConnection() {
}

public Connection connectToServer(String dataBaseName, SshDetails sshDetails) throws SQLException {
this.connectSSH(sshDetails);
this.connection = this.connectToDataBase(dataBaseName, sshDetails);
return this.connection;
}

public void connectSSH(SshDetails sshDetails) throws SQLException {
String sshHost = sshDetails.getSshHost();
String sshUser = sshDetails.getSshUser();
String SshKeyFilepath = sshDetails.getSshKeyFilepath();
int localPort = sshDetails.getLocalPort();
String remoteHost = sshDetails.getRemoteHost();
int remotePort = sshDetails.getRemotePort();
int sshPort = sshDetails.getSshPort();
String driverName = "com.mysql.jdbc.Driver";

try {
Properties config = new Properties();
JSch jsch = new JSch();
if (sshPort == 0) {
this.session = jsch.getSession(sshUser, sshHost, 22);
} else {
this.session = jsch.getSession(sshUser, sshHost, sshPort);
}

jsch.addIdentity(SshKeyFilepath);
config.put("StrictHostKeyChecking", "no");
config.put("ConnectionAttempts", "3");
this.session.setConfig(config);
this.session.connect();
LOGGER.info("SSH Connected");
Class.forName(driverName).newInstance();
int assigned_port = this.session.setPortForwardingL(localPort, remoteHost, remotePort);
LOGGER.info("localhost:" + assigned_port + " -> " + remoteHost + ":" + remotePort);
LOGGER.info("Port Forwarded");
} catch (Exception var13) {
var13.printStackTrace();
}

}

private Connection connectToDataBase(String dataBaseName, SshDetails sshDetails) throws SQLException {
String dbUserName = sshDetails.getDbUserName();
String dbPassword = sshDetails.getDbPassword();
int localPort = sshDetails.getLocalPort();
String localSSHUrl = "localhost";

try {
MysqlDataSource dataSource = new MysqlDataSource();
dataSource.setServerName(localSSHUrl);
dataSource.setPortNumber(localPort);
dataSource.setUser(dbUserName);
dataSource.setAllowMultiQueries(true);
dataSource.setPassword(dbPassword);
dataSource.setDatabaseName(dataBaseName);
this.connection = dataSource.getConnection();
LOGGER.info("Connection to server successful!:" + this.connection + "\n\n");
} catch (Exception var8) {
var8.printStackTrace();
}

return this.connection;
}

public void closeConnections(Connection conn) {
try {
if (conn != null && !conn.isClosed()) {
LOGGER.info("Closing Database Connection");
conn.close();
LOGGER.info("Closing ssh connection");
this.CloseSSHConnection(this.session);
}
} catch (SQLException var3) {
var3.printStackTrace();
}

}

public void CloseSSHConnection(Session session) {
if (session != null && session.isConnected()) {
LOGGER.info("Closing SSH Connection");
session.disconnect();
}

}

public ResultSet executeQuery(String query, Connection conn) {
ResultSet resultSet = null;

try {
Statement stmt = conn.createStatement();
resultSet = stmt.executeQuery(query);
LOGGER.info("Database connection success");
} catch (SQLException var5) {
var5.printStackTrace();
}

return resultSet;
}

public int executeUpdate(String query, Connection conn) throws SQLException {
Statement stmt = conn.createStatement();
int out = stmt.executeUpdate(query);
return out;
}

public int deleteRecord(String col, String value, String table, Connection conn) throws SQLException {
String dQuery = "DELETE FROM " + table + " where " + col + "='" + value + "'";
Statement stmt = conn.createStatement();
int out = stmt.executeUpdate(dQuery);
return out;
}
}