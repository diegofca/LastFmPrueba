package com.dev.android.complice.data.ApiResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macbookpro on 13/10/18.
 */

public class AlbumResponse implements Serializable {

    @SerializedName("albums") private AlbumListResponse albumListResponse;
    public AlbumListResponse getAlbumList(){
        return albumListResponse;
    }

}