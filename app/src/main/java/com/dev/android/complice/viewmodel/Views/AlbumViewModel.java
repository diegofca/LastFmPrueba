package com.dev.android.complice.viewmodel.Views;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.dev.android.complice.data.CompliceApplication;
import com.dev.android.complice.R;
import com.dev.android.complice.data.CompliceConstants;
import com.dev.android.complice.model.Album;
import com.dev.android.complice.model.Gender;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by macbookpro on 13/10/18.
 */

public class AlbumViewModel extends Observable {

    public ObservableInt albumProgress;
    public ObservableInt albumRecycler;
    public ObservableInt albumGenderSpinner;
    public ObservableInt albumLabel;
    public ObservableInt albumErrorLayout;
    public ObservableField<String> messageLabel;
    public ObservableField<String> tittleGender;
    public ObservableArrayList<String> genderList = new ObservableArrayList<>();
    private String selectedGender;
    private List<Album> AllalbumList = new ArrayList<>();
    private List<Album> currentAlbumList = new ArrayList<>();

    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AlbumViewModel(Context context) {
        this.context = context;
        albumProgress = new ObservableInt(View.GONE);
        albumRecycler = new ObservableInt(View.GONE);
        albumErrorLayout = new ObservableInt(View.GONE);
        albumLabel = new ObservableInt(View.VISIBLE);
        albumGenderSpinner = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.error_loading));
        tittleGender= new ObservableField<>("Genero:");
        initializeViews();
        getGenders();
    }

    public void initializeViews() {
        albumLabel.set(View.GONE);
        albumRecycler.set(View.GONE);
        albumProgress.set(View.VISIBLE);
    }

    /// ----- GET ALBUMSSS -----

    public void getTopAlbums() {
        CompliceApplication app = CompliceApplication.create(context);
        String url = CompliceConstants.BASE_URL + CompliceConstants.TOP_ALBUMS + selectedGender + CompliceConstants.API_KEY + CompliceConstants.JSON_FRM;

        Thread thread = new Thread(() -> app.getApiServices().getTopAlbums(url)
                .subscribeOn(app.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(AlbumList -> {

                    changeAlbumsDataSet( AlbumList.getAlbumList().getList() );
                    albumProgress.set(View.GONE);
                    albumLabel.set(View.GONE);
                    albumRecycler.set(View.VISIBLE);
                    albumErrorLayout.set(View.GONE);
                    albumGenderSpinner.set(View.VISIBLE);

                }, throwable -> {

                    messageLabel.set(context.getString(R.string.error_loading));
                    albumProgress.set(View.GONE);
                    albumLabel.set(View.VISIBLE);
                    albumRecycler.set(View.GONE);
                    albumErrorLayout.set(View.VISIBLE);
                    albumGenderSpinner.set(View.GONE);

                })
        );
        thread.start();
    }

    public List<Album> getAlbumslist() {
        return currentAlbumList;
    }
    public List<Album> getAllAlbumslist() {
        return AllalbumList;
    }

    public void setHeaderGender(String headerGender){
        selectedGender = headerGender;
        getTopAlbums();
    }

    private void changeAlbumsDataSet(List<Album> albums) {
        AllalbumList.clear();
        currentAlbumList.clear();
        AllalbumList.addAll(albums);
        currentAlbumList.addAll(albums);
        notObservers();
    }

    public void setAlbumSearchs( List<Album> resultAlbums){
        currentAlbumList.clear();
        currentAlbumList.addAll(resultAlbums);
        setChanged();
        notifyObservers();
    }

    public void allAlbums(){
        currentAlbumList.clear();
        currentAlbumList.addAll(AllalbumList);
    }

    /// ----- GET GENDERS ALBUMS ------

    private void getGenders(){
        CompliceApplication app = CompliceApplication.create(context);

        Thread thread = new Thread(() -> app.getApiServices().getGenders()
                .subscribeOn(app.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genderList -> {

                    changeGendersDataSet( genderList.getGenderList().getList() );
                    sucessService();

                }, throwable -> {

                    messageLabel.set(throwable.getLocalizedMessage());
                    failedService();

                })
        );
        thread.start();
    }

    private void sucessService(){
        albumProgress.set(View.GONE);
        albumLabel.set(View.GONE);
        albumRecycler.set(View.VISIBLE);
        albumErrorLayout.set(View.GONE);
        albumGenderSpinner.set(View.VISIBLE);
    }

    private void failedService(){
        messageLabel.set(context.getString(R.string.error_loading));
        albumProgress.set(View.GONE);
        albumLabel.set(View.VISIBLE);
        albumRecycler.set(View.GONE);
        albumErrorLayout.set(View.VISIBLE);
        albumGenderSpinner.set(View.GONE);
    }

    public List<String> getGenderList() { return genderList; }

    private void changeGendersDataSet(List<Gender> genders) {
        genderList.clear();
        for ( Gender gender : genders ) {
            genderList.add(gender.name);
        }
        setChanged();
        notifyObservers();
    }
    /// -------------------------------------------------------------

    public void OnPlayError(LottieAnimationView view){
        if(!view.isAnimating())
            view.playAnimation();
    }

    public void reloadData(View view) {
        initializeViews();
        getGenders();
        notObservers();
    }

    private void notObservers(){
        setChanged();
        notifyObservers();
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