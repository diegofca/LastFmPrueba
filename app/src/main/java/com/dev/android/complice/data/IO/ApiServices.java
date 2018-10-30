package com.dev.android.complice.data.IO;

import com.dev.android.complice.data.ApiResponse.AlbumResponse;
import com.dev.android.complice.data.ApiResponse.ArtistResponse;
import com.dev.android.complice.data.ApiResponse.GendersResponse;
import com.dev.android.complice.data.ApiResponse.TrackResponse;
import com.dev.android.complice.data.CompliceConstants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by macbookpro on 10/10/18.
 */

public interface ApiServices {

    @GET Observable<AlbumResponse> getTopAlbums(@Url String url);

    @GET(CompliceConstants.BASE_URL + CompliceConstants.TOP_TRACKS + CompliceConstants.API_KEY + CompliceConstants.JSON_FRM)
    Observable<TrackResponse> getTopTracks();

    @GET(CompliceConstants.BASE_URL + CompliceConstants.TOP_ARTIST + CompliceConstants.API_KEY + CompliceConstants.JSON_FRM)
    Observable<ArtistResponse> getTopArtist();

    @GET(CompliceConstants.BASE_URL + CompliceConstants.GET_GENDERS + CompliceConstants.API_KEY + CompliceConstants.JSON_FRM)
    Observable<GendersResponse> getGenders();

}
