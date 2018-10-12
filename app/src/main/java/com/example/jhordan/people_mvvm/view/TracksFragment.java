package com.example.jhordan.people_mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.databinding.TracksFragmentBinding;
import com.example.jhordan.people_mvvm.viewmodel.PagerViewModel;
import com.example.jhordan.people_mvvm.viewmodel.TracksViewModel;

import java.util.Observable;
import java.util.Observer;

public class TracksFragment extends Fragment implements Observer {

    private TracksFragmentBinding tracksFragmentBinding;
    private TracksViewModel tracksViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tracksViewModel = new TracksViewModel(getContext());
        setupObserver(tracksViewModel);
        setupListPeopleView(tracksFragmentBinding.listPeople);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tracksFragmentBinding = DataBindingUtil.inflate( inflater, R.layout.tracks_fragment, container, false);
        View view = tracksFragmentBinding.getRoot();
        tracksFragmentBinding.setTrackViewModel(tracksViewModel);
        return view;
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof PagerViewModel) {
            PeopleAdapter peopleAdapter = (PeopleAdapter) tracksFragmentBinding.listPeople.getAdapter();
            PagerViewModel peopleViewModel = (PagerViewModel) observable;
            peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
        }
    }
}