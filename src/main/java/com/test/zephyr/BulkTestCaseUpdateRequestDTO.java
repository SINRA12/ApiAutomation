package com.test.zephyr;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class BulkTestCaseUpdateRequestDTO implements Serializable {
private static final long serialVersionUID = 6485952136436596891L;
@SerializedName("executions")
@Expose
private List<String> executions = null;
@SerializedName("status")
@Expose
private String status;

public BulkTestCaseUpdateRequestDTO() {
}

public BulkTestCaseUpdateRequestDTO(List<String> executions, String status) {
this.executions = executions;
this.status = status;
}

public List<String> getExecutions() {
return this.executions;
}

public void setExecutions(List<String> executions) {
this.executions = executions;
}

public String getStatus() {
return this.status;
}

public void setStatus(String status) {
this.status = status;
}
}
