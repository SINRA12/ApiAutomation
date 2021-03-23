package com.test.zephyr;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//


import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ZephyrService {
@GET("api/latest/project")
Call<List<GetAllProjectsResponseDTO>> getAllProjects(@Header("Authorization") String var1);

@GET("api/latest/project/{projectId}/versions")
Call<List<GetAllVersionsResponseDTO>> getAllVersions(@Header("Authorization") String var1, @Path("projectId") String var2);

@GET("zapi/latest/cycle")
Call<ResponseBody> getAllCycles(@Header("Authorization") String var1, @Query("projectId") String var2, @Query("versionId") String var3);

@GET("zapi/latest/execution")
Call<GetAllExecutionsResponseDTO> getAllExecutions(@Header("Authorization") String var1, @Query("projectId") String var2, @Query("versionId") String var3, @Query("cycleId") String var4);

@Headers({"Content-Type: application/json"})
@PUT("zapi/latest/execution/{issueId}/execute")
@FormUrlEncoded
Call<ResponseBody> updateTestCaseStatus(@Header("Authorization") String var1, @Path("issueId") String var2, @Field("status") int var3);

@Headers({"Content-Type: application/json"})
@PUT("zapi/latest/execution/updateBulkStatus")
Call<ResponseBody> bulkUpdateTestCaseStatus(@Header("Authorization") String var1, @Body BulkTestCaseUpdateRequestDTO var2);
}