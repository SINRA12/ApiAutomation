package com.test.dingtalk;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

class Markdown {
@SerializedName("title")
@Expose
private String title;
@SerializedName("text")
@Expose
private String text;
@SerializedName("screenshot")
@Expose
private String screenshot;
@SerializedName("report")
@Expose
private Map<String, Integer> report;

public Markdown(String title, String componentName, Integer testCasesNotHavingOwner, String screenshotPath, String text, Map<String, Integer> report) {
this.setTitle(title);
this.setText(componentName, testCasesNotHavingOwner, screenshotPath, text, report.toString());
this.setScreenshot(screenshotPath);
this.setReport(report);
}

public String getText() {
return this.text;
}

public void setText(String componentName, Integer testCasesNotHavingOwner, String screenshotPath, String text, String report) {
this.text = "** Backend Automation Tracker ** \n\n\n " + this.addScreenShot(screenshotPath) + " \n" + this.getBoldText("Project Name = " + componentName) + " \n\n\n \n\n\n" + this.getBoldText("Count of testcases not having owner annotation are = " + testCasesNotHavingOwner) + " \n\n\n Testcase Count per team member : \n\n\n\n\n\n\n " + report.replace("{", "").replace("}", "") + "\n\n\n" + this.getBoldText("Note : Above count only considers testcases having owner annotation and isAutomated flag as true");
}

public String getScreenshot() {
return this.screenshot;
}

public void setScreenshot(String screenshot) {
this.screenshot = screenshot;
}

public Map<String, Integer> getReport() {
return this.report;
}

public void setReport(Map<String, Integer> report) {
this.report = report;
}

public String getTitle() {
return this.title;
}

public void setTitle(String title) {
this.title = title;
}

public String getBoldText(String str) {
return "**" + str + "**";
}

public String addScreenShot(String screenshotPath) {
return "![screenshot](" + screenshotPath + " )\n\n >";
}
}