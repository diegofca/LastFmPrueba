package com.dev.android.lastf.view.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.dev.android.lastf.R;
import com.dev.android.lastf.databinding.AlbumsFragmentBinding;
import com.dev.android.lastf.view.Adapters.AlbumListAdapter;
import com.dev.android.lastf.view.Adapters.SpinnerAdapter;
import com.dev.android.lastf.viewmodel.Views.AlbumViewModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by macbookpro on 13/10/18.
 */

public class AlbumFragment extends Fragment implements Observer {

    private AlbumsFragmentBinding albumsBinding;
    private AlbumViewModel albumsVM;
    public AlbumViewModel getVM(){return albumsVM;}

    // OVERRIDE METHODS
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        albumsBinding = DataBindingUtil.inflate(inflater, R.layout.albums_fragment, container, false);
        View view = albumsBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumsVM = new AlbumViewModel(getActivity());
        albumsBinding.setAlbumViewModel(albumsVM);
        setupObserver(albumsVM);
        setupListAlbumsView(albumsBinding.listAlbum);
        setSpinnerAdapter(albumsBinding.albumGenderSpinner);
    }

    private void setupListAlbumsView(RecyclerView listAlbums) {
        AlbumListAdapter adapter = new AlbumListAdapter();
        listAlbums.setAdapter(adapter);
        listAlbums.setLayoutManager(new GridLayoutManager( getContext(), 2));
    }

    public void setSpinnerAdapter(Spinner spinner){
        List<String> list = albumsBinding.getAlbumViewModel().getGenderList();
        SpinnerAdapter adapter = new SpinnerAdapter(getContext() , list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
                albumsBinding.getAlbumViewModel().setHeaderGender(gender);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof AlbumViewModel) {

            AlbumViewModel albumViewModel = (AlbumViewModel) observable;

            AlbumListAdapter albumListAdapter = (AlbumListAdapter) albumsBinding.listAlbum.getAdapter();
            albumListAdapter.setAlbumList(albumViewModel.getAlbumslist());

            SpinnerAdapter  spinnerAdapter = (SpinnerAdapter) albumsBinding.albumGenderSpinner.getAdapter();
            spinnerAdapter.setGendersist(albumViewModel.getGenderList());
            albumsBinding.getAlbumViewModel().OnPlayError(albumsBinding.errorLoadAnim);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}