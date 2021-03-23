package com.test.Util.reportpublisher;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.test.core.P;
import com.test.core.http.MimeType;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

public class PublishTestResults {
protected static final Logger LOGGER = Logger.getLogger(PublishTestResults.class);
private String url;
private AddTestResult testResults;
private RequestSpecification request;

public PublishTestResults() {
this.url = P.COMMON.get("DASHBOARD_URL");
this.request = RestAssured.given();
this.testResults = new AddTestResult();
this.request.header("Content-Type", MimeType.APPLICATION_JSON.getValue(), new Object[0]);
this.request.header("Accept", "*/*", new Object[0]);
}

public AddTestResult getTestResults() {
return this.testResults;
}

public void publishResults() {
this.request.body(this.testResults.toString());

try {
this.request.post(this.url, new Object[0]);
} catch (Exception var2) {
LOGGER.error("Error Publishing Test Results on Dashboard", var2);
}

}
}
