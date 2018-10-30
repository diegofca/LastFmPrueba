package com.dev.android.complice.viewmodel.Items;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.dev.android.complice.R;
import com.dev.android.complice.model.Track;
import com.dev.android.complice.view.Activities.TrackDetailActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by macbookpro on 13/10/18.
 */

public class ItemTrackViewModel extends BaseObservable {

    public ObservableInt itemTrackProgress;

    private Track track;
    private Context context;

    public ItemTrackViewModel(Track track, Context context) {
        this.context = context;
        this.track = track;
        itemTrackProgress = new ObservableInt(View.GONE);
    }

    // gets
    public String getName() {
        return track.name;
    }
    public String getArtist() {
        return track.artist.name;
    }
    public String getListeners() {
        return track.listeners;
    }
    public String getPictureProfile() { return track.images.get(2).url; }
    public float getProgress(){ return getFavBtn(); }

    @BindingAdapter("app:imageUrl") public static void loadImage(CircleImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_action_music).into(imageView);
    }

    private float getFavBtn( ){
        return track.isFav ? 1 : 0;
    }

    public void onFavClick(LottieAnimationView view) {
        if(!view.isAnimating()){
            if(!track.isFav){
                view.playAnimation();
                track.isFav = true;
            }else{
                track.isFav = false;
                view.setProgress(0);
            }
        }
        setTrack(track);
    }

    public void onItemClick(View view) {
        context.startActivity(TrackDetailActivity.launchDetail(view.getContext(), track));
    }

    public void setTrack(Track track) {
        this.track = track;
        notifyChange();
    }

}
