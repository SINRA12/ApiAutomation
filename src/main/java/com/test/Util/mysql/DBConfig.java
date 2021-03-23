package com.test.Util.mysql;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

public class DBConfig {
private String host;
private String port;
private String uName;
private String password;
private String schemaName;

public DBConfig(String host, String port, String uName, String password, String schemaName) {
this.host = host;
this.port = port;
this.uName = uName;
this.password = password;
this.schemaName = schemaName;
}

public String getHost() {
return this.host;
}

public String getPort() {
return this.port;
}

public String getuName() {
return this.uName;
}

public String getPassword() {
return this.password;
}

public String getSchemaName() {
return this.schemaName;
}

public boolean equals(Object o) {
if (this == o) {
return true;
} else if (!(o instanceof DBConfig)) {
return false;
} else {
DBConfig dbConfig = (DBConfig)o;
return this.schemaName.equals(dbConfig.schemaName);
}
}

public int hashCode() {
return this.schemaName.hashCode();
}
}