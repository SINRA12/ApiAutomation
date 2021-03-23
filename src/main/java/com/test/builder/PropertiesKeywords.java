package com.test.builder;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


public enum PropertiesKeywords {
GENERATE_WORD_REGEX("generate_word\\(\\d+\\)"),
GENERATE_NUMBER_REGEX("generate_number\\(\\d+\\)"),
GENERATE_DATE_REGEX("generate_date\\(.+;-{0,1}\\d+\\)");

private String key;

private PropertiesKeywords(String key) {
this.key = key;
}

public String getKey() {
return this.key;
}

public void setKey(String key) {
this.key = key;
}
}
