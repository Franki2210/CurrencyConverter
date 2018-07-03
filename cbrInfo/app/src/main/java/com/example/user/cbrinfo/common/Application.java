package com.example.user.cbrinfo.common;

import com.example.user.cbrinfo.common.RetrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Application extends android.app.Application {
    private static RetrofitApi retrofitApi;
    private Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.cbr.ru/") //Базовая часть адреса
                .addConverterFactory(SimpleXmlConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        retrofitApi = retrofit.create(RetrofitApi.class);
    }

    public static RetrofitApi getRetrofitApi() {
        return retrofitApi;
    }
}
