package com.example.nazaifmoideen.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NAZAIF MOIDEEN on 30-08-2017.
 */

public class messageResponse {
    @SerializedName("message")
    String message;


    public String getMessage() {
        return message;
    }
}
