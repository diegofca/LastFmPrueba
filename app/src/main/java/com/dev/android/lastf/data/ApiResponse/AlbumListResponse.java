package com.dev.android.lastf.data.ApiResponse;

import com.dev.android.lastf.model.Album;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookpro on 13/10/18.
 */

public class AlbumListResponse  implements Serializable {

    @SerializedName("album") private List<Album> albumsList;
    public List<Album> getList() {
        return albumsList;
    }
    public void setList(List<Album> mAlbumsList) {
        this.albumsList = mAlbumsList;
    }

}
