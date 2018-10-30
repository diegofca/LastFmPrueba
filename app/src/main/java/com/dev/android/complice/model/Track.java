package com.dev.android.complice.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookpro on 10/10/18.
 */

public class Track  implements Serializable {

    @SerializedName("name") public String name;
    @SerializedName("playcount") public String playcount;
    @SerializedName("listeners") public String listeners;
    @SerializedName("url") public String url;
    @SerializedName("artist") public Artist artist;
    @SerializedName("image") public List<Images> images;

    public boolean isFav;

}
