package com.test.customReporter;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.test.Util.annotations.JiraID;
import com.test.Util.reportpublisher.PublishTestResults;
import com.test.zephyr.ZephyrManager;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomReporter implements ITestListener {
protected static final Logger LOGGER = Logger.getLogger(CustomReporter.class);
private PublishTestResults publishTestResults;

public CustomReporter() {
}

public synchronized void onStart(ITestContext context) {
}

public synchronized void onFinish(ITestContext context) {
Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
Iterator var3 = skippedTests.iterator();

while(var3.hasNext()) {
ITestResult temp = (ITestResult)var3.next();
ITestNGMethod method = temp.getMethod();
if (context.getFailedTests().getResults(method).size() > 0) {
skippedTests.remove(temp);
} else if (context.getPassedTests().getResults(method).size() > 0) {
skippedTests.remove(temp);
}
}

var3 = Reporter.getOutput().iterator();

while(var3.hasNext()) {
String s = (String)var3.next();
ExtentManager.getInstance().setTestRunnerOutput(s);
}

ExtentManager.getInstance().flush();
if (System.getProperty("updatejira", "false").equalsIgnoreCase("true")) {
LOGGER.info("------- Updating Results in Zephyr-------");
ZephyrManager.updateResultsInZephyr(this.getJiraIdsList(context.getFailedTests().getAllResults()), "2");
ZephyrManager.updateResultsInZephyr(this.getJiraIdsList(context.getPassedTests().getAllResults()), "1");
}

}

public synchronized void onTestStart(ITestResult result) {
LOGGER.info("Starting new reports");
String methodName = this.getMethodNamewithParams(result);
String className = result.getMethod().getRealClass().getSimpleName();
(new StringBuilder()).append(className).append(".").append(methodName).toString();
ExtentTest extentTest = ExtentManager.getInstance().createTest(methodName, result.getMethod().getDescription());
ExtentManager.setTest(extentTest);
this.addParametersInReport(result);
this.addJiraIDinReport(result);
}

public synchronized void onTestSuccess(ITestResult result) {
LOGGER.info(result.getName() + " = [Pass]\n");
Reporter.log(result.getName() + " = [Pass]<br>");
String className = result.getMethod().getRealClass().getSimpleName();
((ExtentTest)ExtentManager.getTest().get()).assignCategory(new String[]{className});
((ExtentTest)ExtentManager.getTest().get()).pass(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
}

public synchronized void onTestFailure(ITestResult result) {
LOGGER.info(result.getName() + " = [Fail]\n");
Reporter.log(result.getName() + " = [Fail]<br>");
String className = result.getMethod().getRealClass().getSimpleName();
((ExtentTest)ExtentManager.getTest().get()).assignCategory(new String[]{className});
((ExtentTest)ExtentManager.getTest().get()).fail(result.getThrowable());
((ExtentTest)ExtentManager.getTest().get()).fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
}

public synchronized void onTestSkipped(ITestResult result) {
Throwable throwable = result.getThrowable();
if (null != throwable && throwable.getMessage().contains("depends on not successfully finished methods")) {
ExtentTest extentTest = ExtentManager.getInstance().createTest(result.getName(), result.getMethod().getDescription());
ExtentManager.setTest(extentTest);
}

LOGGER.info(result.getName() + " = [Skip]\n");
Reporter.log(result.getName() + " = [Skip]<br>");
String className = result.getMethod().getRealClass().getSimpleName();
((ExtentTest)ExtentManager.getTest().get()).assignCategory(new String[]{className});
((ExtentTest)ExtentManager.getTest().get()).skip(result.getThrowable());
((ExtentTest)ExtentManager.getTest().get()).skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
}

public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
}

private synchronized String takeScreenshot(Object driver, String methodName) {
String directory = "./extentreport/";
String subDirectory = "screenshots/";

try {
DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss_SSS");
String dateName = dateFormat.format(Calendar.getInstance().getTime());
String filePathExtent = subDirectory + methodName + "_" + dateName + ".png";
String filePath = directory + filePathExtent;
File f = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(f, new File(filePath));
return filePathExtent;
} catch (Exception var10) {
LOGGER.info("================== Some Exception occurred while getting screenshot ==================");
return "";
}
}

private synchronized void addJiraIDinReport(ITestResult result) {
String id = this.getJiraIdFromMethod(result);
if (id.length() > 0) {
((ExtentTest)ExtentManager.getTest().get()).log(Status.INFO, MarkupHelper.createLabel("<b>Jira ID : </b>" + id, ExtentColor.CYAN));
}

}

private void addParametersInReport(ITestResult result) {
if (result.getParameters().length > 0 && result.getParameters()[0] instanceof HashMap) {
((ExtentTest)ExtentManager.getTest().get()).log(Status.PASS, MarkupHelper.createTable(this.getParameterArray((HashMap)result.getParameters()[0])));
}

}

private String[][] getParameterArray(HashMap<String, String> hm) {
String[][] parameters = new String[hm.size()][2];
int row = 0;
int column = 0;

for(Iterator var5 = hm.keySet().iterator(); var5.hasNext(); column = 0) {
String str = (String)var5.next();
parameters[row][column] = "<b>" + str + "</b>";
column = column + 1;
parameters[row][column] = (String)hm.get(str);
++row;
}

return parameters;
}

private String getMethodNamewithParams(ITestResult result) {
String methodName = result.getName();
String nextLineCharacter = "<br>";
if (result.getParameters().length > 0 && null != result.getParameters()[0]) {
String paramName = result.getParameters()[0].toString();
methodName = methodName + nextLineCharacter + "(" + paramName + ")";
}

return methodName;
}

private List<String> getJiraIdsList(Set<ITestResult> results) {
Set<String> jiraIdsSet = new HashSet();
Iterator var3 = results.iterator();

while(var3.hasNext()) {
ITestResult iTestResult = (ITestResult)var3.next();

try {
String[] var5 = this.getJiraIdFromMethod(iTestResult).split(",");
int var6 = var5.length;

for(int var7 = 0; var7 < var6; ++var7) {
String jiraID = var5[var7];
jiraIdsSet.add(jiraID);
}
} catch (Exception var9) {
LOGGER.info("JiraId not found for test case = " + this.getMethodNamewithParams(iTestResult));
}
}

jiraIdsSet.remove("");
return new ArrayList(jiraIdsSet);
}

private String getJiraIdFromMethod(ITestResult iTestResult) {
String jiraIDString = "";

try {
if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraID.class) != null) {
jiraIDString = ((JiraID)iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraID.class)).value();
if (jiraIDString.length() > 1 && jiraIDString.equalsIgnoreCase("DataProvider")) {
String str = (String)((HashMap)iTestResult.getParameters()[0]).get("JiraID");
if (str.length() > 1) {
jiraIDString = str;
}
}
}
} catch (Exception var4) {
var4.printStackTrace();
}

return jiraIDString;
}
}