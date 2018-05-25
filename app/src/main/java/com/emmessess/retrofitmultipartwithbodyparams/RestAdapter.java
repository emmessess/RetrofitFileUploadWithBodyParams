package com.emmessess.retrofitmultipartwithbodyparams;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sadiq on 05/25/18.
 */

public class RestAdapter {
    static  String API_BASE_URL = "USE_YOUR_BASE_URL";
    static  Retrofit retrofit = null;


    public static Retrofit getInstance(){
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(API_BASE_URL)
                            .addConverterFactory(
                                    GsonConverterFactory.create()
                            );

            retrofit =
                    builder
                            .client(
                                    httpClient.build()
                            )
                            .build();
        }
        return retrofit;
    }
    @NonNull
    public static RequestBody createPartFromString(String string) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, string);
    }


}
