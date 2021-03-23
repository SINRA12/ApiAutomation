package com.test.core;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.test.Exception.PlaceholderResolverException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class PlaceholderResolver {
protected static final Logger LOGGER = Logger.getLogger(PlaceholderResolver.class);
private static final Pattern PATTERN = Pattern.compile("\\$\\{[^\\{\\}]*\\}");

public PlaceholderResolver() {
}

public static String resolve(Properties properties, String key) {
String value = properties.getProperty(key);
if (value != null) {
Matcher matcher = PATTERN.matcher(value);

while(matcher.find()) {
String placeholder = matcher.group();
String placeholderKey = placeholder.replace("${", "").replace("}", "");
String resolvedValue = resolve(properties, placeholderKey);
if (resolvedValue != null) {
value = value.replace(placeholder, resolvedValue);
}
}
} else if (!key.startsWith("capabilities")) {
LOGGER.warn("Value not resolved by key: " + key);
}

return value;
}

public static boolean isValid(Properties properties) {
Set<Object> keys = properties.keySet();
Iterator var2 = keys.iterator();

while(var2.hasNext()) {
Object key = var2.next();

try {
resolve(properties, (String)key);
} catch (StackOverflowError var5) {
LOGGER.error("Infinit placeholder loop was found for '" + properties.getProperty((String)key) + "'");
return false;
} catch (PlaceholderResolverException var6) {
LOGGER.error(var6.getMessage());
return false;
}
}

return true;
}
}