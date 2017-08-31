package com.example.nazaifmoideen.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NAZAIF MOIDEEN on 30-08-2017.
 */

public class AuthRequest {
    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public AuthRequest(String username,String password){
        this.username = username;
        this.password = password;
    }

}
