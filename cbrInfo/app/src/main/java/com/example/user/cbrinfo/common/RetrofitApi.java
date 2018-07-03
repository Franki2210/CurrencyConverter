package com.example.user.cbrinfo.common;

import com.example.user.cbrinfo.model.ValCurs;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    @GET("scripts/XML_daily.asp/")
    Call<ValCurs> getData();
}

