package com.test.dto;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import java.util.Objects;

public class TestCaseDto {
private String testCaseName;
private String emailId;
private String expectedResult;
private String testDescription;
private String traceability;
private Boolean isAutomated;
private Boolean isEnabled;
private String[] groups;
private String[] groupsDependedUpon;

public TestCaseDto() {
}

public String[] getGroups() {
return this.groups;
}

public void setGroups(String[] groups) {
this.groups = groups;
}

public String[] getGroupsDependedUpon() {
return this.groupsDependedUpon;
}

public void setGroupsDependedUpon(String[] groupsDependedUpon) {
this.groupsDependedUpon = groupsDependedUpon;
}

public String getEmailId() {
return this.emailId;
}

public void setEmailId(String emailId) {
this.emailId = emailId;
}

public String getExpectedResult() {
return this.expectedResult;
}

public void setExpectedResult(String expectedResult) {
this.expectedResult = expectedResult;
}

public String getTestDescription() {
return this.testDescription;
}

public void setTestDescription(String testDescription) {
this.testDescription = testDescription;
}

public Boolean getAutomated() {
return this.isAutomated;
}

public void setAutomated(Boolean automated) {
this.isAutomated = automated;
}

public String getTestCaseName() {
return this.testCaseName;
}

public void setTestCaseName(String testCaseName) {
this.testCaseName = testCaseName;
}

public String getTraceability() {
return this.traceability;
}

public void setTraceability(String traceability) {
this.traceability = traceability;
}

public Boolean getIsEnabled() {
return this.isEnabled;
}

public void setIsEnabled(Boolean isEnabled) {
this.isEnabled = isEnabled;
}

public boolean equals(Object o) {
if (this == o) {
return true;
} else if (o != null && this.getClass() == o.getClass()) {
TestCaseDto that = (TestCaseDto)o;
return Objects.equals(this.testCaseName, that.testCaseName);
} else {
return false;
}
}

public int hashCode() {
return Objects.hash(new Object[]{this.testCaseName});
}
}