package com.dev.android.complice.viewmodel.Views;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.dev.android.complice.model.Album;
import com.dev.android.complice.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class PagerViewModel extends Observable {

    public ObservableInt pagerProgress;
    public ObservableInt pagerTabs;
    public ObservableInt pagerView;
    public ObservableInt pagerSearchView;
    private int pagerCurrent;

    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PagerViewModel(@NonNull Context context) {
        this.context = context;
        initializeViews();
    }

    public void initializeViews() {
        pagerProgress = new ObservableInt(View.VISIBLE);
        pagerSearchView = new ObservableInt(View.GONE);
        pagerTabs = new ObservableInt(View.VISIBLE);
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public List<Track> resultTracks(String searchText, List<Track> allTracks){
        List<Track> resultSearch = new ArrayList<>();
        for (Track track : allTracks ) {
            if(track.name.toUpperCase().contains(searchText.toUpperCase())){
                resultSearch.add(track);
            }
        }
        return resultSearch;
    }

    public List<Album> resultAlbums(String searchText, List<Album> allAlbums){
        List<Album> resultSearch = new ArrayList<>();
        for (Album album : allAlbums ) {
            if(album.name.toUpperCase().contains(searchText.toUpperCase())){
                resultSearch.add(album);
            }
        }
        return resultSearch;
    }

    public void setPagerCurrent(int pos){
        pagerCurrent = pos;
    }

    public int getPagerCurrent(){return pagerCurrent;}

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

}
