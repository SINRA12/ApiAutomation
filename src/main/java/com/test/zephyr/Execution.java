package com.test.zephyr;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Execution {
@Expose
private Integer id;
@SerializedName("orderId")
@Expose
private Integer orderId;
@SerializedName("executionStatus")
@Expose
private String executionStatus;
@SerializedName("comment")
@Expose
private String comment;
@SerializedName("htmlComment")
@Expose
private String htmlComment;
@SerializedName("cycleId")
@Expose
private Integer cycleId;
@SerializedName("cycleName")
@Expose
private String cycleName;
@SerializedName("versionId")
@Expose
private Integer versionId;
@SerializedName("versionName")
@Expose
private String versionName;
@SerializedName("projectId")
@Expose
private Integer projectId;
@SerializedName("createdBy")
@Expose
private Object createdBy;
@SerializedName("modifiedBy")
@Expose
private Object modifiedBy;
@SerializedName("issueId")
@Expose
private Integer issueId;
@SerializedName("issueKey")
@Expose
private String issueKey;
@SerializedName("summary")
@Expose
private String summary;
@SerializedName("issueDescription")
@Expose
private String issueDescription;
@SerializedName("label")
@Expose
private String label;
@SerializedName("component")
@Expose
private String component;
@SerializedName("projectKey")
@Expose
private String projectKey;
@SerializedName("canViewIssue")
@Expose
private Boolean canViewIssue;
@SerializedName("executionDefectCount")
@Expose
private Integer executionDefectCount;
@SerializedName("stepDefectCount")
@Expose
private Integer stepDefectCount;
@SerializedName("totalDefectCount")
@Expose
private Integer totalDefectCount;

public Execution() {
}

public Execution(Integer id, Integer orderId, String executionStatus, String comment, String htmlComment, Integer cycleId, String cycleName, Integer versionId, String versionName, Integer projectId, Object createdBy, Object modifiedBy, Integer issueId, String issueKey, String summary, String issueDescription, String label, String component, String projectKey, Boolean canViewIssue, Integer executionDefectCount, Integer stepDefectCount, Integer totalDefectCount) {
this.id = id;
this.orderId = orderId;
this.executionStatus = executionStatus;
this.comment = comment;
this.htmlComment = htmlComment;
this.cycleId = cycleId;
this.cycleName = cycleName;
this.versionId = versionId;
this.versionName = versionName;
this.projectId = projectId;
this.createdBy = createdBy;
this.modifiedBy = modifiedBy;
this.issueId = issueId;
this.issueKey = issueKey;
this.summary = summary;
this.issueDescription = issueDescription;
this.label = label;
this.component = component;
this.projectKey = projectKey;
this.canViewIssue = canViewIssue;
this.executionDefectCount = executionDefectCount;
this.stepDefectCount = stepDefectCount;
this.totalDefectCount = totalDefectCount;
}

public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getOrderId() {
return this.orderId;
}

public void setOrderId(Integer orderId) {
this.orderId = orderId;
}

public String getExecutionStatus() {
return this.executionStatus;
}

public void setExecutionStatus(String executionStatus) {
this.executionStatus = executionStatus;
}

public String getComment() {
return this.comment;
}

public void setComment(String comment) {
this.comment = comment;
}

public String getHtmlComment() {
return this.htmlComment;
}

public void setHtmlComment(String htmlComment) {
this.htmlComment = htmlComment;
}

public Integer getCycleId() {
return this.cycleId;
}

public void setCycleId(Integer cycleId) {
this.cycleId = cycleId;
}

public String getCycleName() {
return this.cycleName;
}

public void setCycleName(String cycleName) {
this.cycleName = cycleName;
}

public Integer getVersionId() {
return this.versionId;
}

public void setVersionId(Integer versionId) {
this.versionId = versionId;
}

public String getVersionName() {
return this.versionName;
}

public void setVersionName(String versionName) {
this.versionName = versionName;
}

public Integer getProjectId() {
return this.projectId;
}

public void setProjectId(Integer projectId) {
this.projectId = projectId;
}

public Object getCreatedBy() {
return this.createdBy;
}

public void setCreatedBy(Object createdBy) {
this.createdBy = createdBy;
}

public Object getModifiedBy() {
return this.modifiedBy;
}

public void setModifiedBy(Object modifiedBy) {
this.modifiedBy = modifiedBy;
}

public Integer getIssueId() {
return this.issueId;
}

public void setIssueId(Integer issueId) {
this.issueId = issueId;
}

public String getIssueKey() {
return this.issueKey;
}

public void setIssueKey(String issueKey) {
this.issueKey = issueKey;
}

public String getSummary() {
return this.summary;
}

public void setSummary(String summary) {
this.summary = summary;
}

public String getIssueDescription() {
return this.issueDescription;
}

public void setIssueDescription(String issueDescription) {
this.issueDescription = issueDescription;
}

public String getLabel() {
return this.label;
}

public void setLabel(String label) {
this.label = label;
}

public String getComponent() {
return this.component;
}

public void setComponent(String component) {
this.component = component;
}

public String getProjectKey() {
return this.projectKey;
}

public void setProjectKey(String projectKey) {
this.projectKey = projectKey;
}

public Boolean getCanViewIssue() {
return this.canViewIssue;
}

public void setCanViewIssue(Boolean canViewIssue) {
this.canViewIssue = canViewIssue;
}

public Integer getExecutionDefectCount() {
return this.executionDefectCount;
}

public void setExecutionDefectCount(Integer executionDefectCount) {
this.executionDefectCount = executionDefectCount;
}

public Integer getStepDefectCount() {
return this.stepDefectCount;
}

public void setStepDefectCount(Integer stepDefectCount) {
this.stepDefectCount = stepDefectCount;
}

public Integer getTotalDefectCount() {
return this.totalDefectCount;
}

public void setTotalDefectCount(Integer totalDefectCount) {
this.totalDefectCount = totalDefectCount;
}
}
