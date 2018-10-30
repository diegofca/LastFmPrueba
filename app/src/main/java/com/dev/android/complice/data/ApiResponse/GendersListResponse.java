package com.dev.android.complice.data.ApiResponse;

import com.dev.android.complice.model.Gender;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookpro on 13/10/18.
 */

public class GendersListResponse implements Serializable {

    @SerializedName("tag") private List<Gender> genders;
    public List<Gender> getList() {
        return genders;
    }
    public void setList(List<Gender> mgendersList) {
        this.genders = mgendersList;
    }

}
