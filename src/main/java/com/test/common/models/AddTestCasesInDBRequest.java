package com.test.common.models;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.test.dto.TestCaseDto;
import java.util.Set;

@JsonIgnoreProperties(
ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class AddTestCasesInDBRequest {
@JsonProperty("project_name")
private String projectName;
@JsonProperty("testcases")
private Set<TestCaseDto> bunchOfTestcases;

public AddTestCasesInDBRequest() {
}

public String getProjectName() {
return this.projectName;
}

public void setProjectName(String projectName) {
this.projectName = projectName;
}

public Set<TestCaseDto> getBunchOfTestcases() {
return this.bunchOfTestcases;
}

public void setBunchOfTestcases(Set<TestCaseDto> bunchOfTestcases) {
this.bunchOfTestcases = bunchOfTestcases;
}
}
