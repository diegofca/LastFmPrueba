package com.dev.android.complice.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookpro on 10/10/18.
 */

public class Artist implements Serializable {

    @SerializedName("name") public String name;
    @SerializedName("playcount") public String playcount;
    @SerializedName("listeners") public String listeners;
    @SerializedName("url") public String url;
    @SerializedName("image") public List<Images> images;

    public boolean isFav;

}
