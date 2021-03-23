package com.test.customReporter;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.test.core.P;
import com.test.global.GlobalData;
import com.test.Listner.SuiteListener;

public class ExtentManager {
private static ExtentReports extent = null;
private static ThreadLocal<ExtentTest> test = new ThreadLocal();

private ExtentManager() {
}

public static synchronized ThreadLocal<ExtentTest> getTest() {
return test;
}

public static synchronized void setTest(ExtentTest test) {
getTest().set(test);
}

public static synchronized ExtentReports getInstance() {
if (extent == null) {
createInstance();
}

return extent;
}

public static synchronized ExtentReports createInstance() {
ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentreport/extentreport.html");
htmlReporter.config().setDocumentTitle("Paytm Automation Testing Report");
htmlReporter.config().setReportName("Automation Report - (" + SuiteListener.componentName + ")");
htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
htmlReporter.config().setTheme(Theme.STANDARD);
htmlReporter.config().setEncoding("utf-8");
htmlReporter.config().setChartVisibilityOnOpen(true);
htmlReporter.config().setJS(GlobalData.JS_CHANGENAME_EXTENTREPOTS);
extent = new ExtentReports();
extent.attachReporter(new ExtentReporter[]{htmlReporter});
return extent;
}

public static void addExecutionDetails_ExtentReport() {
extent.setSystemInfo("Pass %", MarkupHelper.createLabel(GlobalData.PASSPERCENTAGE, ExtentColor.GREEN).getMarkup());
if (P.EXECUTION_DETAILS.getBoolean("ADD_ADDITIONAL_EXECUTION_DETAILS")) {
extent.setSystemInfo("App Version", P.EXECUTION_DETAILS.get("BUILD_VERSION"));
extent.setSystemInfo("App Build Stamp", P.EXECUTION_DETAILS.get("BUILD_TIMESTAMP"));
extent.setSystemInfo("Environment", P.EXECUTION_DETAILS.get("ENVIRONMENT"));
extent.setSystemInfo("ExecutionType", P.EXECUTION_DETAILS.get("EXECUTION_TYPE"));
if (!GlobalData.EXECUTION_TYPE.equalsIgnoreCase("System")) {
String finalStr = "";
String[] var1 = GlobalData.GROUP_NAMES.split(",");
int var2 = var1.length;

for(int var3 = 0; var3 < var2; ++var3) {
String str = var1[var3];
if (str.contains(".")) {
finalStr = finalStr + str.split("\\.")[1] + "<br>";
} else {
finalStr = finalStr + str + "<br>";
}
}

extent.setSystemInfo("Groups", finalStr);
}

extent.setSystemInfo("Language", P.EXECUTION_DETAILS.get("LANGUAGE"));
}

extent.flush();
}
}