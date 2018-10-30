package com.dev.android.complice.viewmodel.Views;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.dev.android.complice.R;
import com.dev.android.complice.model.Track;
import com.squareup.picasso.Picasso;

/**
 * Created by macbookpro on 13/10/18.
 */

public class TrackDetailViewModel {

    private Track track;
    private Context context;
    public TrackDetailViewModel(Track track, Context context) {
        this.track = track;
        this.context = context;
    }

    //Getters
    public String getTrackName() { return track.name; }
    public String getArtistName() { return track.artist.name; }
    public String getListeners() { return track.listeners + context.getString(R.string.label_oyentes);}
    public String getCounters() { return track.playcount + context.getString(R.string.label_reproducciones); }
    public String getPictureProfile() {
        return track.images.get(3).url;
    }

    @BindingAdapter("app:imageUrl") public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_action_music).into(imageView);
    }
}
