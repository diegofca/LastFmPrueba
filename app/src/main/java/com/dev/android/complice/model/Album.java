package com.dev.android.complice.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookpro on 10/10/18.
 */

public class Album implements Serializable {

    @SerializedName("name") public String name;
    @SerializedName("url") public String url;
    @SerializedName("artist") public Artist artist;
    @SerializedName("image") public List<Images> images;

    public boolean isFav;

}
