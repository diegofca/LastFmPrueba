package com.dev.android.complice.viewmodel.Items;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.dev.android.complice.R;
import com.dev.android.complice.model.Artist;
import com.squareup.picasso.Picasso;

import java.util.Observable;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by macbookpro on 14/10/18.
 */

public class ItemArtistViewModel extends Observable {

    private Artist artist;
    private Context context;

    public ItemArtistViewModel(Artist artist, Context context) {
        this.context = context;
        this.artist = artist;
    }

    // gets
    public String getName() {return artist.name;}
    public String getPictureProfile() { return artist.images.get(2).url; }
    public float getProgress(){ return getFavBtn(); }

    @BindingAdapter("app:imageUrl") public static void loadImage(CircleImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_action_music).into(imageView);
    }

    private float getFavBtn( ){
        return artist.isFav ? 1 : 0;
    }

    public void onFavClick(LottieAnimationView view) {
        if(!view.isAnimating()){
            if(!artist.isFav){
                view.playAnimation();
                artist.isFav = true;
            }else{
                artist.isFav = false;
                view.setProgress(0);
            }
        }
        setArtist(artist);
    }

    public void onItemClick(View view) {

    }

    public void setArtist(Artist artist) {
        this.artist = artist;
        notifyObservers();
    }

}
