package com.test.builder;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PropertiesProcessorMain {
private static List<PropertiesProcessor> processors = new ArrayList();

public PropertiesProcessorMain() {
}

public static Properties processProperties(Properties in) {
Properties out = new Properties();
Iterator var2 = processors.iterator();

while(var2.hasNext()) {
PropertiesProcessor processor = (PropertiesProcessor)var2.next();
out.putAll(processor.process(in));
}

return out;
}

static {
processors.add(new GenerateProcessor());
}
}
