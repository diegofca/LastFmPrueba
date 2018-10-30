package com.dev.android.complice.data.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macbookpro on 13/10/18.
 */

public class GendersResponse implements Serializable {

    @SerializedName("toptags") private GendersListResponse gendersListResponse;
    public GendersListResponse getGenderList(){
        return gendersListResponse;
    }

}

