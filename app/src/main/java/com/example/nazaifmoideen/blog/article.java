package com.example.nazaifmoideen.blog;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NAZAIF MOIDEEN on 02-09-2017.
 */

public class article {

    @SerializedName("id")
    Integer id;

    @SerializedName("title")
    String title;

    @SerializedName("date")
    String date;

    @SerializedName("heading")
    String heading;

    @SerializedName("content")
    String content;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHeading() {
        return heading;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
