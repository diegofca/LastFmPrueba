package com.dev.android.complice.data.ApiResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macbookpro on 13/10/18.
 */

public class TrackResponse implements Serializable {

    @SerializedName("tracks") private TrackListResponse trackListResponse;
    public TrackListResponse getTrackList(){
        return trackListResponse;
    }

}
