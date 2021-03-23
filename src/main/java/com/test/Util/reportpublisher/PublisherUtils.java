package com.test.Util.reportpublisher;


import com.test.core.P;
import com.test.customReporter.RunENV;
import com.test.Listner.SuiteListener;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteResult;

public class PublisherUtils {
protected static final Logger LOGGER = Logger.getLogger(PublisherUtils.class);

public PublisherUtils() {
}

public static void addTestResultForDashboard(ISuite iSuite, long startTime, long endTime) {
String env = System.getProperty("runEnv", (String)null);
String buildNumber = System.getProperty("build_number", (String)null);
String jobName = System.getProperty("job_name", (String)null);
if (null != RunENV.isValidEnv(env) && null != buildNumber && null != jobName) {
try {
int failedCount = 0;
int passedCount = 0;
int skippedCount = 0;
Map<String, ISuiteResult> results = iSuite.getResults();

Entry rs;
for(Iterator var12 = results.entrySet().iterator(); var12.hasNext(); skippedCount += ((ISuiteResult)rs.getValue()).getTestContext().getSkippedTests().size()) {
rs = (Entry)var12.next();
failedCount += ((ISuiteResult)rs.getValue()).getTestContext().getFailedTests().size();
passedCount += ((ISuiteResult)rs.getValue()).getTestContext().getPassedTests().size();
}

PublishTestResults publishTestResults = new PublishTestResults();
AddTestResult result = publishTestResults.getTestResults();
int testCaseCount = failedCount + passedCount + skippedCount;
result.setProjectName(SuiteListener.componentName);
result.setStartTime(startTime);
result.setEndTime(endTime);
result.setTotalCases(testCaseCount);
result.setFailedCases(failedCount);
result.setSuccessCases(passedCount);
result.setSkippedCases(skippedCount);
result.setEnvironment(env.toLowerCase());
result.setReportFile(P.COMMON.get("HTML_BASE_DIR") + jobName + File.separator + buildNumber + File.separator + "HTML_20Report/");
if (testCaseCount > 0) {
LOGGER.info("Publishing Test Stats ::::::::: " + result.toString());
publishTestResults.publishResults();
}
} catch (Exception var15) {
LOGGER.error("Error Publishing Stats for Test Execution, Project Name :::::::::: " + SuiteListener.componentName);
}
}

}
}
