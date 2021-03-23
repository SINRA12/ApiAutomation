package com.test.common.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.common.models.AddTestCasesInDBRequest;
import com.test.core.AbstractApi;
import com.test.core.P;
import com.test.core.http.HttpMethodType;
import com.test.core.http.MimeType;
import com.test.dto.TestCaseDto;
import java.util.Set;

public class AddTestCasesInDB extends AbstractApi {
	public AddTestCasesInDB(String projectName, Set<TestCaseDto> testcases) {
		super(MimeType.APPLICATION_JSON, P.COMMON.get("ADD_TESTCASES"), HttpMethodType.POST);
		AddTestCasesInDBRequest addTests = new AddTestCasesInDBRequest();
		addTests.setProjectName(projectName);
		addTests.setBunchOfTestcases(testcases);
		ObjectMapper mapper = new ObjectMapper();
		String reqBody = null;

		try {
			reqBody = mapper.writeValueAsString(addTests);
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		this.requestParams.setRequestBody(reqBody);
	}

	public void call() {
		LOGGER.info(this.callAPI().asString());
	}
}