package com.test.common.steps;

//

//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.common.models.PublishStatsRequest;
import com.test.core.AbstractApi;
import com.test.core.P;
import com.test.core.http.HttpMethodType;
import com.test.core.http.MimeType;
import java.util.Map;

public class PublishStats extends AbstractApi {
	public PublishStats(String projectName, Map<String, Integer> map) {
		super(MimeType.APPLICATION_JSON, P.COMMON.get("PUBLISH_STATS"), HttpMethodType.POST);
		PublishStatsRequest publishStats = new PublishStatsRequest();
		publishStats.setProjectName(projectName);
		publishStats.setTestcaseCount(map);
		ObjectMapper mapper = new ObjectMapper();
		String reqBody = null;

		try {
			reqBody = mapper.writeValueAsString(publishStats);
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		this.requestParams.setRequestBody(reqBody);
	}

	public void publishData() {
		LOGGER.info(this.callAPI().asString());
	}
}
