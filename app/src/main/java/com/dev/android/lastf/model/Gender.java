package com.dev.android.lastf.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macbookpro on 11/10/18.
 */

public class Gender implements Serializable {

    @SerializedName("name") public String name;
    @SerializedName("url") public String url;
    @SerializedName("reach") public String reach;
    @SerializedName("taggings") public String taggings;

}
