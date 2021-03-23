package com.test.dingtalk;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DingService {
@Headers({"Content-Type: application/json"})
@POST("robot/send")
Call<ResponseBody> sendMessage(@Query("access_token") String var1, @Body MarkDownMessageDTO var2);
}
