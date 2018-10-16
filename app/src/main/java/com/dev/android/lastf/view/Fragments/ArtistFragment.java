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
import com.dev.android.lastf.databinding.ArtistFragmentBinding;
import com.dev.android.lastf.view.Adapters.ArtistListAdapter;
import com.dev.android.lastf.viewmodel.Views.ArtistViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by macbookpro on 14/10/18.
 */

public class ArtistFragment extends Fragment implements Observer {
    private ArtistFragmentBinding artistBinding;
    private ArtistViewModel artistVM;
    public ArtistViewModel getVM(){return artistVM;}

    // OVERRIDE METHODS
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        artistBinding = DataBindingUtil.inflate( inflater, R.layout.artist_fragment, container, false);
        View view = artistBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        artistVM = new ArtistViewModel( getActivity());
        artistBinding.setArtistViewModel(artistVM);
        setupObserver(artistVM);
        setupListArtistView(artistBinding.listArtist);
    }

    private void setupListArtistView(RecyclerView listArtist) {
        ArtistListAdapter adapter = new ArtistListAdapter();
        listArtist.setAdapter(adapter);
        listArtist.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof ArtistViewModel) {
            ArtistListAdapter artistListAdapter = (ArtistListAdapter) artistBinding.listArtist.getAdapter();
            ArtistViewModel artistViewModel = (ArtistViewModel) observable;
            artistListAdapter.setArtistList(artistViewModel.getArtistList( ));

            artistBinding.getArtistViewModel().OnPlayError(artistBinding.errorLoadAnim);

        }
    }
}
