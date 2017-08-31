package com.example.nazaifmoideen.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NAZAIF MOIDEEN on 30-08-2017.
 */

public class ErrorResponse {
    @SerializedName("error")
    String error;


    public String getError() {
        return error;
    }
}
