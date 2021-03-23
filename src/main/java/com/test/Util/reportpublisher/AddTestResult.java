package com.test.Util.reportpublisher;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.Gson;
import java.io.Serializable;

@JsonIgnoreProperties(
ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class AddTestResult implements Serializable {
@JsonProperty(
required = true
)
private String projectName;
private int totalCases;
private int successCases;
private int failedCases;
private int skippedCases;
private long startTime;
private long endTime;
private String reportFile;
private String environment = "local";

@JsonCreator
public AddTestResult() {
}

public String getProjectName() {
return this.projectName;
}

public void setProjectName(String projectName) {
this.projectName = projectName;
}

public int getTotalCases() {
return this.totalCases;
}

public void setTotalCases(int totalCases) {
this.totalCases = totalCases;
}

public int getSuccessCases() {
return this.successCases;
}

public void setSuccessCases(int successCases) {
this.successCases = successCases;
}

public int getFailedCases() {
return this.failedCases;
}

public void setFailedCases(int failedCases) {
this.failedCases = failedCases;
}

public int getSkippedCases() {
return this.skippedCases;
}

public void setSkippedCases(int skippedCases) {
this.skippedCases = skippedCases;
}

public long getStartTime() {
return this.startTime;
}

public void setStartTime(long startTime) {
this.startTime = startTime;
}

public long getEndTime() {
return this.endTime;
}

public void setEndTime(long endTime) {
this.endTime = endTime;
}

public String getReportFile() {
return this.reportFile;
}

public void setReportFile(String reportFile) {
this.reportFile = reportFile;
}

public String getEnvironment() {
return this.environment;
}

public void setEnvironment(String environment) {
this.environment = environment;
}

public String toString() {
Gson gson = new Gson();
return gson.toJson(this, AddTestResult.class);
}
}