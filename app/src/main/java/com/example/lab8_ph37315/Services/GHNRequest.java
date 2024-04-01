package com.example.lab8_ph37315.Services;

import static com.example.lab8_ph37315.Services.GHNServices.GHN_URL;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GHNRequest {
    public final static String ShopID = "191654";
    public final static String TokenGHN = "9dbf1fd4-ed65-11ee-b1d4-92b443b7a897";
    private GHNServices ghnServices;

    public GHNRequest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("ShopId",ShopID)
                        .addHeader("Token",TokenGHN)
                        .build();
                return chain.proceed(request);
            }
        });

        ghnServices = new Retrofit.Builder()
                .baseUrl(GHN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build().create(GHNServices.class);
    }
    public GHNServices callAPI(){
        return ghnServices;
    }
}
