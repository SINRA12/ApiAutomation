package com.test.zephyr;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetAllExecutionsResponseDTO {
@SerializedName("executions")
@Expose
private List<Execution> executions = null;
@SerializedName("currentlySelectedExecutionId")
@Expose
private String currentlySelectedExecutionId;
@SerializedName("totalExecutions")
@Expose
private Integer totalExecutions;
@SerializedName("totalExecuted")
@Expose
private Integer totalExecuted;
@SerializedName("recordsCount")
@Expose
private Integer recordsCount;

public GetAllExecutionsResponseDTO() {
}

public GetAllExecutionsResponseDTO(List<Execution> executions, String currentlySelectedExecutionId, Integer totalExecutions, Integer totalExecuted, Integer recordsCount) {
this.executions = executions;
this.currentlySelectedExecutionId = currentlySelectedExecutionId;
this.totalExecutions = totalExecutions;
this.totalExecuted = totalExecuted;
this.recordsCount = recordsCount;
}

public List<Execution> getExecutions() {
return this.executions;
}

public void setExecutions(List<Execution> executions) {
this.executions = executions;
}

public String getCurrentlySelectedExecutionId() {
return this.currentlySelectedExecutionId;
}

public void setCurrentlySelectedExecutionId(String currentlySelectedExecutionId) {
this.currentlySelectedExecutionId = currentlySelectedExecutionId;
}

public Integer getTotalExecutions() {
return this.totalExecutions;
}

public void setTotalExecutions(Integer totalExecutions) {
this.totalExecutions = totalExecutions;
}

public Integer getTotalExecuted() {
return this.totalExecuted;
}

public void setTotalExecuted(Integer totalExecuted) {
this.totalExecuted = totalExecuted;
}

public Integer getRecordsCount() {
return this.recordsCount;
}

public void setRecordsCount(Integer recordsCount) {
this.recordsCount = recordsCount;
}
}
