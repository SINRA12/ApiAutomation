package com.test.common.models;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(
ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class PublishStatsRequest implements Serializable {
@JsonProperty("project_name")
private String projectName;
@JsonProperty("testcase_count")
private Map<String, Integer> testcaseCount;

public PublishStatsRequest() {
}

public String getProjectName() {
return this.projectName;
}

public void setProjectName(String projectName) {
this.projectName = projectName;
}

public Map<String, Integer> getTestcaseCount() {
return this.testcaseCount;
}

public void setTestcaseCount(Map<String, Integer> testcaseCount) {
this.testcaseCount = testcaseCount;
}
}
