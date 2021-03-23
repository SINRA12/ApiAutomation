package com.test.zephyr;

//

//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.test.core.P;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Credentials;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZephyrManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZephyrManager.class);
	static Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
	static String baseURL = "https://jira.mypaytm.com/rest/";
	static Retrofit retrofit;
	static ZephyrService service;
	static String credentials;
	static Map<String, String> testcaseIDMap;

	public ZephyrManager() {
	}

	public static String getProjectID(String projectName) throws Exception {
		Call<List<GetAllProjectsResponseDTO>> call = service.getAllProjects(credentials);
		List<GetAllProjectsResponseDTO> getAllProjectsResponseList = (List) call.execute().body();
		Iterator var3 = getAllProjectsResponseList.iterator();

		GetAllProjectsResponseDTO getAllProjectsResponseDTO;
		do {
			if (!var3.hasNext()) {
				return null;
			}

			getAllProjectsResponseDTO = (GetAllProjectsResponseDTO) var3.next();
		} while (!getAllProjectsResponseDTO.getName().equalsIgnoreCase(projectName));

		return getAllProjectsResponseDTO.getId();
	}

	public static String getVersionID(String projectID, String versionName) throws Exception {
		Call<List<GetAllVersionsResponseDTO>> call = service.getAllVersions(credentials, projectID);
		List<GetAllVersionsResponseDTO> getAllVersionsResponseDTOList = (List) call.execute().body();
		Iterator var4 = getAllVersionsResponseDTOList.iterator();

		GetAllVersionsResponseDTO getAllVersionsResponseDTO;
		do {
			if (!var4.hasNext()) {
				return null;
			}

			getAllVersionsResponseDTO = (GetAllVersionsResponseDTO) var4.next();
		} while (!getAllVersionsResponseDTO.getName().equalsIgnoreCase(versionName));

		return getAllVersionsResponseDTO.getId();
	}

	public static String getCycleID(String projectID, String versionID, String cycleName) throws Exception {
		Call<ResponseBody> call = service.getAllCycles(credentials, projectID, versionID);
		ResponseBody responseBody = (ResponseBody) call.execute().body();
		JsonObject jsonObject = (JsonObject) (new JsonParser()).parse(responseBody.string());
		Iterator var6 = jsonObject.keySet().iterator();

		String key;
		GetAllCyclesResponseDTO cyclesResponseDTO;
		do {
			if (!var6.hasNext()) {
				return null;
			}

			key = (String) var6.next();
			cyclesResponseDTO = (GetAllCyclesResponseDTO) (new Gson()).fromJson(jsonObject.getAsJsonObject(key),
					GetAllCyclesResponseDTO.class);
		} while (!cyclesResponseDTO.getName().equalsIgnoreCase(cycleName));

		return key;
	}

	public static Map<String, String> getAllExecutions(String projectID, String versionID, String cycleID)
			throws Exception {
		Map<String, String> issueKeyIDMap = new HashMap();
		Call<GetAllExecutionsResponseDTO> call = service.getAllExecutions(credentials, projectID, versionID, cycleID);
		GetAllExecutionsResponseDTO getAllExecutionsResponseDTO = (GetAllExecutionsResponseDTO) call.execute().body();
		Iterator var6 = getAllExecutionsResponseDTO.getExecutions().iterator();

		while (var6.hasNext()) {
			Execution execution = (Execution) var6.next();
			issueKeyIDMap.put(execution.getIssueKey(), String.valueOf(execution.getId()));
		}

		return issueKeyIDMap;
	}

	public static void updateTestCaseStatus(String issueID, int status) throws Exception {
		Call<ResponseBody> call = service.updateTestCaseStatus(credentials, issueID, status);
		LOGGER.info(((ResponseBody) call.execute().body()).string());
	}

	public static void bulkUpdateTestCaseStatus(BulkTestCaseUpdateRequestDTO bulkTestCaseUpdateRequestDTO)
			throws Exception {
		LOGGER.info("***" + service.bulkUpdateTestCaseStatus(credentials, bulkTestCaseUpdateRequestDTO).execute()
				.errorBody().toString());
		LOGGER.info(((ResponseBody) service.bulkUpdateTestCaseStatus(credentials, bulkTestCaseUpdateRequestDTO)
				.execute().body()).string());
	}

	static void init() throws Exception {
		if (testcaseIDMap == null) {
			String projectID = getProjectID(P.JIRA.get("jiraProjectName"));
			String versionID = getVersionID(projectID, P.JIRA.get("jiraVersionId"));
			String cycleID = getCycleID(projectID, versionID, P.JIRA.get("jiraCycleName"));
			testcaseIDMap = getAllExecutions(projectID, versionID, cycleID);
		}

	}

	static void updateResults(List<String> jiraIDs, String status) throws Exception {
		List<String> issueIds = new ArrayList();

		String jiraID;
		for (Iterator var3 = jiraIDs.iterator(); var3.hasNext(); issueIds.add(testcaseIDMap.get(jiraID))) {
			jiraID = (String) var3.next();
			if (testcaseIDMap.get(jiraID) != null) {
			}
		}

		BulkTestCaseUpdateRequestDTO bulkTestCaseUpdateRequestDTO = new BulkTestCaseUpdateRequestDTO(issueIds, status);
		bulkUpdateTestCaseStatus(bulkTestCaseUpdateRequestDTO);
	}

	public static void updateResultsInZephyr(List<String> jiraIDs, String status) {
		if (jiraIDs.size() >= 1) {
			if (status.equalsIgnoreCase("1")) {
				LOGGER.info("Jira Ids for Passed Cases = \n" + gson.toJson(jiraIDs));
			} else {
				LOGGER.info("Jira Ids for Failed Cases = \n" + gson.toJson(jiraIDs));
			}

			try {
				init();
				updateResults(jiraIDs, status);
			} catch (Exception var3) {
				var3.printStackTrace();
			}
		}

	}

	static {
		retrofit = (new Builder()).addConverterFactory(GsonConverterFactory.create()).baseUrl(baseURL).build();
		service = (ZephyrService) retrofit.create(ZephyrService.class);
		credentials = Credentials.basic(P.JIRA.get("jiraUserName"), P.JIRA.get("jiraPassword"));
	}
}