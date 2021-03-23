package com.test.zephyr;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllProjectsResponseDTO {
@SerializedName("expand")
@Expose
private String expand;
@SerializedName("self")
@Expose
private String self;
@SerializedName("id")
@Expose
private String id;
@SerializedName("key")
@Expose
private String key;
@SerializedName("name")
@Expose
private String name;
@SerializedName("projectTypeKey")
@Expose
private String projectTypeKey;

public GetAllProjectsResponseDTO() {
}

public GetAllProjectsResponseDTO(String expand, String self, String id, String key, String name, String projectTypeKey) {
this.expand = expand;
this.self = self;
this.id = id;
this.key = key;
this.name = name;
this.projectTypeKey = projectTypeKey;
}

public String getExpand() {
return this.expand;
}

public void setExpand(String expand) {
this.expand = expand;
}

public String getSelf() {
return this.self;
}

public void setSelf(String self) {
this.self = self;
}

public String getId() {
return this.id;
}

public void setId(String id) {
this.id = id;
}

public String getKey() {
return this.key;
}

public void setKey(String key) {
this.key = key;
}

public String getName() {
return this.name;
}

public void setName(String name) {
this.name = name;
}

public String getProjectTypeKey() {
return this.projectTypeKey;
}

public void setProjectTypeKey(String projectTypeKey) {
this.projectTypeKey = projectTypeKey;
}
}
