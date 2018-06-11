package com.mobile.app.maxmoney.Model;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit2.Call;

public interface OrderAPI {
    @Headers({"api-key : c8836431a26239f8cb18c6d0988c9a89effb38db"})
    @retrofit2.http.GET("/v1/orders/current")
    public void getActivity(Callback<Response> callback);

}