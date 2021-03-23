package com.test.zephyr;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllCyclesResponseDTO {
@SerializedName("totalExecutions")
@Expose
private Integer totalExecutions;
@SerializedName("endDate")
@Expose
private String endDate;
@SerializedName("description")
@Expose
private String description;
@SerializedName("totalExecuted")
@Expose
private Integer totalExecuted;
@SerializedName("started")
@Expose
private String started;
@SerializedName("versionName")
@Expose
private String versionName;
@SerializedName("expand")
@Expose
private String expand;
@SerializedName("projectKey")
@Expose
private String projectKey;
@SerializedName("versionId")
@Expose
private Integer versionId;
@SerializedName("environment")
@Expose
private String environment;
@SerializedName("totalCycleExecutions")
@Expose
private Integer totalCycleExecutions;
@SerializedName("totalDefects")
@Expose
private Integer totalDefects;
@SerializedName("build")
@Expose
private String build;
@SerializedName("createdBy")
@Expose
private String createdBy;
@SerializedName("ended")
@Expose
private String ended;
@SerializedName("name")
@Expose
private String name;
@SerializedName("totalFolders")
@Expose
private Integer totalFolders;
@SerializedName("modifiedBy")
@Expose
private String modifiedBy;
@SerializedName("projectId")
@Expose
private Integer projectId;
@SerializedName("createdByDisplay")
@Expose
private String createdByDisplay;
@SerializedName("startDate")
@Expose
private String startDate;

public GetAllCyclesResponseDTO() {
}

public GetAllCyclesResponseDTO(Integer totalExecutions, String endDate, String description, Integer totalExecuted, String started, String versionName, String expand, String projectKey, Integer versionId, String environment, Integer totalCycleExecutions, Integer totalDefects, String build, String createdBy, String ended, String name, Integer totalFolders, String modifiedBy, Integer projectId, String createdByDisplay, String startDate) {
this.totalExecutions = totalExecutions;
this.endDate = endDate;
this.description = description;
this.totalExecuted = totalExecuted;
this.started = started;
this.versionName = versionName;
this.expand = expand;
this.projectKey = projectKey;
this.versionId = versionId;
this.environment = environment;
this.totalCycleExecutions = totalCycleExecutions;
this.totalDefects = totalDefects;
this.build = build;
this.createdBy = createdBy;
this.ended = ended;
this.name = name;
this.totalFolders = totalFolders;
this.modifiedBy = modifiedBy;
this.projectId = projectId;
this.createdByDisplay = createdByDisplay;
this.startDate = startDate;
}

public Integer getTotalExecutions() {
return this.totalExecutions;
}

public void setTotalExecutions(Integer totalExecutions) {
this.totalExecutions = totalExecutions;
}

public String getEndDate() {
return this.endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

public String getDescription() {
return this.description;
}

public void setDescription(String description) {
this.description = description;
}

public Integer getTotalExecuted() {
return this.totalExecuted;
}

public void setTotalExecuted(Integer totalExecuted) {
this.totalExecuted = totalExecuted;
}

public String getStarted() {
return this.started;
}

public void setStarted(String started) {
this.started = started;
}

public String getVersionName() {
return this.versionName;
}

public void setVersionName(String versionName) {
this.versionName = versionName;
}

public String getExpand() {
return this.expand;
}

public void setExpand(String expand) {
this.expand = expand;
}

public String getProjectKey() {
return this.projectKey;
}

public void setProjectKey(String projectKey) {
this.projectKey = projectKey;
}

public Integer getVersionId() {
return this.versionId;
}

public void setVersionId(Integer versionId) {
this.versionId = versionId;
}

public String getEnvironment() {
return this.environment;
}

public void setEnvironment(String environment) {
this.environment = environment;
}

public Integer getTotalCycleExecutions() {
return this.totalCycleExecutions;
}

public void setTotalCycleExecutions(Integer totalCycleExecutions) {
this.totalCycleExecutions = totalCycleExecutions;
}

public Integer getTotalDefects() {
return this.totalDefects;
}

public void setTotalDefects(Integer totalDefects) {
this.totalDefects = totalDefects;
}

public String getBuild() {
return this.build;
}

public void setBuild(String build) {
this.build = build;
}

public String getCreatedBy() {
return this.createdBy;
}

public void setCreatedBy(String createdBy) {
this.createdBy = createdBy;
}

public String getEnded() {
return this.ended;
}

public void setEnded(String ended) {
this.ended = ended;
}

public String getName() {
return this.name;
}

public void setName(String name) {
this.name = name;
}

public Integer getTotalFolders() {
return this.totalFolders;
}

public void setTotalFolders(Integer totalFolders) {
this.totalFolders = totalFolders;
}

public String getModifiedBy() {
return this.modifiedBy;
}

public void setModifiedBy(String modifiedBy) {
this.modifiedBy = modifiedBy;
}

public Integer getProjectId() {
return this.projectId;
}

public void setProjectId(Integer projectId) {
this.projectId = projectId;
}

public String getCreatedByDisplay() {
return this.createdByDisplay;
}

public void setCreatedByDisplay(String createdByDisplay) {
this.createdByDisplay = createdByDisplay;
}

public String getStartDate() {
return this.startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}
}