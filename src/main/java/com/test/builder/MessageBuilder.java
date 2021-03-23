package com.test.builder;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

public class MessageBuilder {
private static Configuration freemarkerConfiguration = new Configuration();

public MessageBuilder() {
}

public static final synchronized String buildStringMessage(String templatePath, Properties... propertiesArr) {
Template template;
try {
template = freemarkerConfiguration.getTemplate(templatePath);
} catch (IOException var20) {
throw new RuntimeException(var20);
}

Properties resultProperties = new Properties();
Properties[] var4 = propertiesArr;
int var5 = propertiesArr.length;

for(int var6 = 0; var6 < var5; ++var6) {
Properties properties = var4[var6];
resultProperties.putAll(properties);
}

StringWriter sw = new StringWriter();

try {
template.process(resultProperties, sw);
} catch (TemplateException var17) {
throw new RuntimeException(var17);
} catch (IOException var18) {
throw new RuntimeException(var18);
} finally {
try {
sw.close();
} catch (IOException var16) {
throw new RuntimeException(var16);
}
}

return sw.getBuffer().toString();
}

static {
freemarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(MessageBuilder.class, "/"));
}
}
