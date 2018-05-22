package com.example.devspeks.maxmoney.Model;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface LoginAPI {
    @FormUrlEncoded
    @POST("/cdd-app/process/login.php")
    public void loginUser(
            @Field("username") String username,
            @Field("password") String password,
            Callback<Response> callback);
}