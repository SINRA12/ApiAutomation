package com.test.validation;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


public enum JsonCompareKeywords {
SKIP("skip"),
TYPE("type:"),
REGEX("regex:"),
ARRAY_CONTAINS("validate_array_contains_only:");

private String key;

private JsonCompareKeywords(String key) {
this.key = key;
}

public String getKey() {
return this.key;
}

public void setKey(String key) {
this.key = key;
}
}
