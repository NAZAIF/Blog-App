package com.example.nazaifmoideen.blog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NAZAIF MOIDEEN on 30-08-2017.
 */

public class APIManager {

    private static APIinterface apIinterface;

    private static void createAPInterface(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkURL.BaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apIinterface = retrofit.create(APIinterface.class);
    }

    public static APIinterface getAPInterface(){
        if(apIinterface == null){
            createAPInterface();
        }
        return apIinterface;
    }

}
