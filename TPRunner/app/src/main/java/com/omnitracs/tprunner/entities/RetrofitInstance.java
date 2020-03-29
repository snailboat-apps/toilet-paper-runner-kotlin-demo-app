package com.omnitracs.tprunner.entities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit mRetrofit;

    // TODO use for object / singleton

    private static final String STORE_DATA_URL = "http://10.0.2.2:8080/"; //"http://192.168.1.254:8080";

    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(STORE_DATA_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }
}
