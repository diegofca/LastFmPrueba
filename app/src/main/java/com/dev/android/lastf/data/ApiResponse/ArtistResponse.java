package com.dev.android.lastf.data.ApiResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macbookpro on 14/10/18.
 */

public class ArtistResponse implements Serializable {

    @SerializedName("artists") private ArtistListResponse artistListResponse;
    public ArtistListResponse getArtistList(){
        return artistListResponse;
    }

}
