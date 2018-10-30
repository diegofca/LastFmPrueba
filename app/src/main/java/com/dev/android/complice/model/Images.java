package com.dev.android.complice.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macbookpro on 11/10/18.
 */

public class Images implements Serializable {

    @SerializedName("#text") public String url;
    @SerializedName("size") public String size;

}
