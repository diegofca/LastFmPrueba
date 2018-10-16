package com.dev.android.lastf.data.ApiResponse;

import com.dev.android.lastf.model.Artist;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookpro on 14/10/18.
 */

public class ArtistListResponse implements Serializable {

    @SerializedName("artist") private List<Artist> artistsList;
    public List<Artist> getList() {
        return artistsList;
    }
    public void setList(List<Artist> mList) {
        this.artistsList = mList;
    }

}
