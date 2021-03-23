package com.test.Util.misc;
//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import java.util.Properties;

public class PropertiesUtil {
public PropertiesUtil() {
}

public static Properties readProperties(String path) {
Properties prop = new Properties();

try {
prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(path));
return prop;
} catch (Exception var3) {
throw new RuntimeException("Can not read properties from file", var3);
}
}
}