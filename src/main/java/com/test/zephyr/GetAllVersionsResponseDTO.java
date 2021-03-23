package com.test.zephyr;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllVersionsResponseDTO {
@SerializedName("self")
@Expose
private String self;
@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("archived")
@Expose
private Boolean archived;
@SerializedName("released")
@Expose
private Boolean released;
@SerializedName("startDate")
@Expose
private String startDate;
@SerializedName("releaseDate")
@Expose
private String releaseDate;
@SerializedName("userStartDate")
@Expose
private String userStartDate;
@SerializedName("userReleaseDate")
@Expose
private String userReleaseDate;
@SerializedName("projectId")
@Expose
private Integer projectId;

public GetAllVersionsResponseDTO() {
}

public GetAllVersionsResponseDTO(String self, String id, String name, Boolean archived, Boolean released, String startDate, String releaseDate, String userStartDate, String userReleaseDate, Integer projectId) {
this.self = self;
this.id = id;
this.name = name;
this.archived = archived;
this.released = released;
this.startDate = startDate;
this.releaseDate = releaseDate;
this.userStartDate = userStartDate;
this.userReleaseDate = userReleaseDate;
this.projectId = projectId;
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

public String getName() {
return this.name;
}

public void setName(String name) {
this.name = name;
}

public Boolean getArchived() {
return this.archived;
}

public void setArchived(Boolean archived) {
this.archived = archived;
}

public Boolean getReleased() {
return this.released;
}

public void setReleased(Boolean released) {
this.released = released;
}

public String getStartDate() {
return this.startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public String getReleaseDate() {
return this.releaseDate;
}

public void setReleaseDate(String releaseDate) {
this.releaseDate = releaseDate;
}

public String getUserStartDate() {
return this.userStartDate;
}

public void setUserStartDate(String userStartDate) {
this.userStartDate = userStartDate;
}

public String getUserReleaseDate() {
return this.userReleaseDate;
}

public void setUserReleaseDate(String userReleaseDate) {
this.userReleaseDate = userReleaseDate;
}

public Integer getProjectId() {
return this.projectId;
}

public void setProjectId(Integer projectId) {
this.projectId = projectId;
}
}
