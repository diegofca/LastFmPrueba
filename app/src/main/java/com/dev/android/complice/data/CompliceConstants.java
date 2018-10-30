package com.dev.android.complice.data;

/**
 * Created by macbookpro on 10/10/18.
 */

public class CompliceConstants {

    public static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    public static final String API_KEY = "&api_key=de0fea0b3f321c36d6f189d1db9f2f7c";

    public static final String JSON_FRM = "&format=json";

    public static final String TOP_ARTIST = "?method=chart.gettopartists";
    public static final String TOP_ALBUMS = "?method=tag.gettopalbums&tag=";
    public static final String TOP_TRACKS = "?method=chart.gettoptracks";
    public static final String GET_GENDERS = "?method=tag.getTopTags";


    public static final String LABEL_TOP_TRACKS  = "Top Tracks";
    public static final String LABEL_TOP_ALBUMS  = "Top Albums";
    public static final String LABEL_TOP_ARTISTS = "Top Artists";


    public static final int LIMIT_PAGE_VIEW_PAGER = 2;
    public static final int TRACKS_PAGE = 0;
    public static final int ALBUMS_PAGE = 1;
    public static final int ARTIST_PAGE = 2;

    public static final String PROJECT_URL = "https://github.com/diegofca/LastFmPrueba";
}
