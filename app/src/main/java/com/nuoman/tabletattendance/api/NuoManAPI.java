package com.nuoman.tabletattendance.api;

import com.squareup.okhttp.RequestBody;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Total API
 * AUTHOR: Alex
 * DATE: 21/10/2015 18:44
 */
public interface NuoManAPI {

    String URL = "http://120.27.137.6:8080/AttendenceMachineSys/";

    /**
     * 通用接口调用
     *
     * @param methodName //     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("{part}/{methodName}")
    Call<String> serviceAPI(@Path("part") String part, @Path("methodName") String methodName, @FieldMap() Map<String, String> map);

    @GET("{part}/{methodName}")
    Call<String> serviceGetAPI(@Path("part") String part, @Path("methodName") String methodName, @QueryMap() Map<String, String> map);



    //图片上传

    @Multipart
    @POST("uploadFile")
    Call<String> uploadFile(@PartMap Map<String, RequestBody> params);

}


