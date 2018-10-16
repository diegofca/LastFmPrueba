package com.dev.android.lastf.view.Activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import com.dev.android.lastf.R;
import com.dev.android.lastf.databinding.TrackDetailActivityBinding;
import com.dev.android.lastf.model.Track;
import com.dev.android.lastf.viewmodel.Views.TrackDetailViewModel;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by macbookpro on 13/10/18.
 */

public class TrackDetailActivity extends BaseActivity  implements Observer {

    private static final String EXTRA_TRACK = "EXTRA_TRACK";

    private TrackDetailActivityBinding trackDetailActivityBinding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.track_detail_activity);
        setSupportActionBar(trackDetailActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
        setStatusBarTransparent();
    }

    public static Intent launchDetail(Context context, Track track) {
        Intent intent = new Intent(context, TrackDetailActivity.class);
        intent.putExtra(EXTRA_TRACK, track);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        Track track = (Track) getIntent().getSerializableExtra(EXTRA_TRACK);
        TrackDetailViewModel trackDetailViewModel = new TrackDetailViewModel(track, this);
        trackDetailActivityBinding.setTrackDetailViewModel(trackDetailViewModel);
        setTitle(track.name);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
