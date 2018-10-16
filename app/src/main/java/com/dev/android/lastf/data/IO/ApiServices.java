package com.dev.android.lastf.data.IO;

import com.dev.android.lastf.data.ApiResponse.AlbumResponse;
import com.dev.android.lastf.data.ApiResponse.ArtistResponse;
import com.dev.android.lastf.data.ApiResponse.GendersResponse;
import com.dev.android.lastf.data.ApiResponse.TrackResponse;
import com.dev.android.lastf.data.LastConstants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by macbookpro on 10/10/18.
 */

public interface ApiServices {

    @GET Observable<AlbumResponse> getTopAlbums(@Url String url);

    @GET(LastConstants.BASE_URL + LastConstants.TOP_TRACKS + LastConstants.API_KEY + LastConstants.JSON_FRM)
    Observable<TrackResponse> getTopTracks();

    @GET(LastConstants.BASE_URL + LastConstants.TOP_ARTIST + LastConstants.API_KEY + LastConstants.JSON_FRM)
    Observable<ArtistResponse> getTopArtist();

    @GET(LastConstants.BASE_URL + LastConstants.GET_GENDERS + LastConstants.API_KEY + LastConstants.JSON_FRM)
    Observable<GendersResponse> getGenders();

}
