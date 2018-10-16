package com.dev.android.lastf.viewmodel.Views;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.dev.android.lastf.data.LastFmApplication;
import com.dev.android.lastf.R;
import com.dev.android.lastf.model.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by macbookpro on 14/10/18.
 */

public class ArtistViewModel extends Observable {

    public ObservableInt artistProgress;
    public ObservableInt artistRecycler;
    public ObservableInt artistLabel;
    public ObservableInt artistErrorLayout;
    public ObservableField<String> messageLabel;
    private List<Artist> artisList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ArtistViewModel(Context context) {
        this.context = context;
        this.artisList = new ArrayList<>();
        artistProgress = new ObservableInt(View.GONE);
        artistRecycler = new ObservableInt(View.GONE);
        artistLabel = new ObservableInt(View.VISIBLE);
        artistErrorLayout = new ObservableInt(View.GONE);
        messageLabel = new ObservableField<>(context.getString(R.string.error_loading));
        initializeViews();
        getTopArtist();
    }

    public void initializeViews() {
        artistLabel.set(View.GONE);
        artistRecycler.set(View.GONE);
        artistProgress.set(View.VISIBLE);
    }


    public void getTopArtist(){
        LastFmApplication app = LastFmApplication.create(context);
        Thread thread = new Thread(() -> app.getApiServices().getTopArtist()
                .subscribeOn(app.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( Artistlist -> {

                    changeArtistDataSet(Artistlist.getArtistList().getList());
                    artistProgress.set(View.GONE);
                    artistLabel.set(View.GONE);
                    artistRecycler.set(View.VISIBLE);
                    artistErrorLayout.set(View.GONE);

                }, throwable -> {

                    messageLabel.set(context.getString(R.string.error_loading));
                    artistProgress.set(View.GONE);
                    artistLabel.set(View.VISIBLE);
                    artistRecycler.set(View.GONE);
                    artistErrorLayout.set(View.VISIBLE);

                })
        );
        thread.start();
    }

    private void changeArtistDataSet(List<Artist> artists) {
        artisList.addAll(artists);
        notObservers();
    }

    public List<Artist> getArtistList() {
        return artisList;
    }


    public void reloadData(View view) {
        initializeViews();
        getTopArtist();
        notObservers();
    }

    private void notObservers(){
        setChanged();
        notifyObservers();
    }

    public void OnPlayError(LottieAnimationView view){
        if(!view.isAnimating())
            view.playAnimation();
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}

