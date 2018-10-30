package com.dev.android.complice.viewmodel.Items;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.dev.android.complice.R;
import com.dev.android.complice.model.Album;
import com.squareup.picasso.Picasso;

import java.util.Observable;

/**
 * Created by macbookpro on 13/10/18.
 */

public class ItemAlbumViewModel extends Observable {

    private Album album;
    private Context context;

    public ItemAlbumViewModel(Album album, Context context) {
        this.context = context;
        this.album = album;
    }

    // gets
    public String getName() {
        return album.name;
    }
    public String getArtist() { return album.artist.name;}
    public String getPictureProfile() { return album.images.get(2).url;}
    public float getProgress(){ return getFavBtn();}

    @BindingAdapter("app:imageUrl") public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_action_music).into(imageView);
    }

    private float getFavBtn( ){
        return album.isFav ? 1 : 0;
    }

    public void onFavClick(LottieAnimationView view) {
        if(!view.isAnimating()){
            if(!album.isFav){
                view.playAnimation();
                album.isFav = true;
            }else{
                album.isFav = false;
                view.setProgress(0);
            }
        }
        setAlbum(album);
    }

    public void onItemClick(View view) {

    }

    public void setAlbum(Album album) {
        this.album = album;
        notifyObservers();
    }
}
