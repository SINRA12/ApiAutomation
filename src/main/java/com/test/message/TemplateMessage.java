package com.test.message;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.test.builder.MessageBuilder;
import com.test.builder.PropertiesProcessorMain;
import com.test.Util.misc.PropertiesUtil;
import java.util.Iterator;
import java.util.Properties;
import org.apache.commons.configuration2.CompositeConfiguration;

public class TemplateMessage extends Message {
private String templatePath;
private CompositeConfiguration compositeConfiguration;
private Properties[] propertiesArr;
private Properties propertiesStorage = new Properties();
private String propertiesPath;

public TemplateMessage() {
}

public String getTemplatePath() {
return this.templatePath;
}

public void setTemplatePath(String templatePath) {
this.templatePath = templatePath;
}

public Properties[] getPropertiesArr() {
return this.propertiesArr;
}

public void setPropertiesArr(Properties... propertiesArr) {
this.propertiesArr = propertiesArr;
Properties[] var2 = propertiesArr;
int var3 = propertiesArr.length;

for(int var4 = 0; var4 < var3; ++var4) {
Properties properties = var2[var4];
this.propertiesStorage.putAll(properties);
}

}

public CompositeConfiguration getCompositeConfiguration() {
return this.compositeConfiguration;
}

public void setEnvironmentConfiguration(CompositeConfiguration compositeConfiguration) {
this.compositeConfiguration = compositeConfiguration;
Iterator keys = compositeConfiguration.getKeys();

while(keys.hasNext()) {
String key = (String)keys.next();
this.propertiesStorage.put(key, compositeConfiguration.getProperty(key));
}

}

public String getPropertiesPath() {
return this.propertiesPath;
}

public void setPropertiesPath(String propertiesPath) {
this.propertiesPath = propertiesPath;
this.propertiesStorage.putAll(PropertiesUtil.readProperties(propertiesPath));
}

public Properties getPropertiesStorage() {
return this.propertiesStorage;
}

public void setPropertiesStorage(Properties propertiesStorage) {
this.propertiesStorage = propertiesStorage;
}

public void putItemToPropertiesStorage(String key, Object value) {
this.propertiesStorage.put(key, value);
}

public void removeItemFromPropertiesStorage(String key) {
this.propertiesStorage.remove(key);
}

public String getMessageText() {
this.propertiesStorage = PropertiesProcessorMain.processProperties(this.propertiesStorage);
return MessageBuilder.buildStringMessage(this.templatePath, new Properties[]{this.propertiesStorage});
}
}