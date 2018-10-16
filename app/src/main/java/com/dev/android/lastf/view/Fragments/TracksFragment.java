package com.dev.android.lastf.view.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.android.lastf.R;
import com.dev.android.lastf.databinding.TracksFragmentBinding;
import com.dev.android.lastf.view.Adapters.TrackListAdapter;
import com.dev.android.lastf.viewmodel.Views.TracksViewModel;

import java.util.Observable;
import java.util.Observer;

public class TracksFragment extends Fragment implements Observer {

    private TracksFragmentBinding tracksBinding;
    private TracksViewModel tracksVM;
    public TracksViewModel getVM(){return tracksVM;}

    // OVERRIDE METHODS
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tracksBinding = DataBindingUtil.inflate( inflater, R.layout.tracks_fragment, container, false);
        View view = tracksBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tracksVM = new TracksViewModel( getActivity());
        tracksBinding.setTrackViewModel(tracksVM);
        setupObserver(tracksVM);
        setupListTracksView(tracksBinding.listTrack);
    }

    private void setupListTracksView(RecyclerView listTracks) {
        TrackListAdapter adapter = new TrackListAdapter();
        listTracks.setAdapter(adapter);
        listTracks.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof TracksViewModel) {

            ((TrackListAdapter) tracksBinding.listTrack.getAdapter()).setTrackList( tracksVM.getTracksList( ));
            tracksBinding.getTrackViewModel().OnPlayError(tracksBinding.errorLoadAnim);

        }
    }
}