package com.dev.android.complice.data.ApiResponse;

import com.dev.android.complice.model.Track;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookpro on 13/10/18.
 */

public class TrackListResponse implements Serializable {

    @SerializedName("track") private List<Track> trackList;
    public List<Track> getList() {
        return trackList;
    }
    public void setList(List<Track> mtrackList) {
        this.trackList = mtrackList;
    }

}
