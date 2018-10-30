package com.dev.android.complice.viewmodel.Views;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.dev.android.complice.data.CompliceApplication;
import com.dev.android.complice.R;
import com.dev.android.complice.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class TracksViewModel extends Observable {

    public ObservableInt trackProgress;
    public ObservableInt trackRecycler;
    public ObservableInt trackLabel;
    public ObservableInt trackErrorLayout;
    public ObservableField<String> messageLabel;
    private List<Track> alltrackList = new ArrayList<>();
    private List<Track> currentTrackList = new ArrayList<>();
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public TracksViewModel(Context context) {
        this.context = context;
        trackProgress = new ObservableInt(View.GONE);
        trackRecycler = new ObservableInt(View.GONE);
        trackErrorLayout = new ObservableInt(View.GONE);
        trackLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>("");
        initializeViews();
        getTopTracks();
    }

    public void initializeViews() {
        trackLabel.set(View.GONE);
        trackRecycler.set(View.GONE);
        trackProgress.set(View.VISIBLE);
        trackErrorLayout.set(View.GONE);
    }

    public void getTopTracks(){
        CompliceApplication app = CompliceApplication.create(context);

        Thread thread = new Thread(() -> app.getApiServices().getTopTracks()
                .subscribeOn( app.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( Trackslist -> {

                    changeTracksDataSet(Trackslist.getTrackList().getList());
                    trackProgress.set(View.GONE);
                    trackLabel.set(View.GONE);
                    trackRecycler.set(View.VISIBLE);
                    trackErrorLayout.set(View.GONE);

                }, throwable -> {

                    messageLabel.set(context.getString(R.string.error_loading));
                    trackErrorLayout.set(View.VISIBLE);
                    trackProgress.set(View.GONE);
                    trackLabel.set(View.VISIBLE);
                    trackRecycler.set(View.GONE);

                })
        );
        thread.start();
    }

    private void changeTracksDataSet(List<Track> tracks) {
        alltrackList.addAll(tracks);
        currentTrackList.addAll(tracks);
        notObservers();
    }

    public void setTrackSearchs( List<Track> resultTracks){
        currentTrackList.clear();
        currentTrackList.addAll(resultTracks);
        notObservers();
    }

    public void allTracks(){
        currentTrackList.clear();
        currentTrackList.addAll(alltrackList);
    }

    public void reloadData(View view) {
        initializeViews();
        getTopTracks();
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

    public List<Track> getAllTracksList() {
        return alltrackList;
    }

    public List<Track> getTracksList() {
        return currentTrackList;
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
