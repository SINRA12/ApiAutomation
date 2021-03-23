package com.test.dingtalk;

//

//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class DingManager {
	static Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
	static String dingBaseURL = "https://oapi.dingtalk.com/";
	static Retrofit retrofit;
	static DingService service;

	public DingManager() {
	}

	public static void sendMarkDownMessage(String accessToken, String componentName, Map<String, Integer> report,
			Integer testcasesNotHavingOwner, String screenshotPath) {
		try {
			MarkDownMessageDTO automationTrackerDTO = new MarkDownMessageDTO("Automation Tracker", componentName,
					testcasesNotHavingOwner, screenshotPath, "", report);
			Call<ResponseBody> call = service.sendMessage(accessToken, automationTrackerDTO);
			System.out.println(((ResponseBody) call.execute().body()).string());
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}

	static {
		retrofit = (new Builder()).addConverterFactory(GsonConverterFactory.create()).baseUrl(dingBaseURL).build();
		service = (DingService) retrofit.create(DingService.class);
	}
}