package com.test.core;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.test.Exception.InvalidConfigurationException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public enum P {
API("api.properties"),
CONFIG("config.properties"),
TESTDATA("testdata.properties"),
EMAIL("email.properties"),
DATABASE("database.properties"),
JIRA("jira.properties"),
EXECUTION_DETAILS("executionDetails.properties"),
COMMON("common.properties");

private static final Logger LOGGER = Logger.getLogger(P.class);
private static final String OVERRIDE_SIGN = "_";
private static Map<String, Properties> propertiesHolder = new HashMap();
private String resourceFile;

private P(String resourceKey) {
this.resourceFile = resourceKey;
}

public static String getResourcePath(String resource) {
String path = StringUtils.removeStart(ClassLoader.getSystemResource(resource).getPath(), "/");
path = StringUtils.replaceChars(path, "/", "\\");
path = StringUtils.replaceChars(path, "!", "");
return path;
}

public void put(String key, String value) {
((Properties)propertiesHolder.get(this.resourceFile)).put(key, value);
}

public boolean containsKey(String key) {
return CONFIG.resourceFile.equals(this.resourceFile) ? ((Properties)propertiesHolder.get(this.resourceFile)).containsKey(key) : ((Properties)propertiesHolder.get(this.resourceFile)).containsKey(key);
}

public String get(String key) {
String value = CONFIG.resourceFile.equals(this.resourceFile) ? PlaceholderResolver.resolve((Properties)propertiesHolder.get(this.resourceFile), key) : ((Properties)propertiesHolder.get(this.resourceFile)).getProperty(key);
return value != null ? value : "";
}

public int getInt(String key) {
return Integer.parseInt(this.get(key));
}

public long getLong(String key) {
return Long.parseLong(this.get(key));
}

public double getDouble(String key) {
return Double.parseDouble(this.get(key));
}

public boolean getBoolean(String key) {
return Boolean.valueOf(this.get(key));
}

public Properties getProperties() {
return (Properties)propertiesHolder.get(this.resourceFile);
}

static {
P[] var0 = values();
int var1 = var0.length;

for(int var2 = 0; var2 < var1; ++var2) {
P resource = var0[var2];

try {
Properties properties = new Properties();
URL baseResource = ClassLoader.getSystemResource(resource.resourceFile);
if (baseResource != null) {
properties.load(baseResource.openStream());
LOGGER.info("Base properties loaded: " + resource.resourceFile);
}

URL overrideResource;
for(String resourceName = "_" + resource.resourceFile; (overrideResource = ClassLoader.getSystemResource(resourceName)) != null; resourceName = "_" + resourceName) {
properties.load(overrideResource.openStream());
LOGGER.info("Following properties loaded: " + resourceName);
}

Iterator var8 = properties.keySet().iterator();

while(var8.hasNext()) {
Object key = var8.next();
String systemValue = System.getProperty((String)key);
if (!StringUtils.isEmpty(systemValue)) {
properties.put(key, systemValue);
}
}

if (resource.resourceFile.contains("config.properties")) {
String prefix = "capabilities.";
Map<String, String> javaProperties = new HashMap(System.getProperties());
Iterator var17 = javaProperties.entrySet().iterator();

while(var17.hasNext()) {
Entry<String, String> entry = (Entry)var17.next();
String key = (String)entry.getKey();
if (key.toLowerCase().startsWith("capabilities.")) {
String value = (String)entry.getValue();
if (!StringUtils.isEmpty(value)) {
properties.put(key, value);
}
}
}
}

propertiesHolder.put(resource.resourceFile, properties);
} catch (Exception var14) {
throw new InvalidConfigurationException("Invalid config in '" + resource + "': " + var14.getMessage());
}
}

}
}
