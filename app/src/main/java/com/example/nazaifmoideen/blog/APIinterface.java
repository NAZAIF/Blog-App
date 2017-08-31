package com.example.nazaifmoideen.blog;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by NAZAIF MOIDEEN on 30-08-2017.
 */

public interface APIinterface {

    @POST(NetworkURL.LOGIN)
    Call<messageResponse> login(@Body AuthRequest Body);

    @POST(NetworkURL.REGISTRATION)
    Call<messageResponse> registration(@Body AuthRequest Body);

}
