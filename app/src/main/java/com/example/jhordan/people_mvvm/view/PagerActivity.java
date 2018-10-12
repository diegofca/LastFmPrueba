package com.example.jhordan.people_mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.data.PeopleFactory;
import com.example.jhordan.people_mvvm.databinding.PagerActivityBinding;
import com.example.jhordan.people_mvvm.viewmodel.PagerViewModel;

import java.util.Observable;
import java.util.Observer;

public class PagerActivity extends AppCompatActivity implements Observer {

    private PagerActivityBinding pagerActivityBinding;
    private PagerViewModel pagerViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(pagerActivityBinding.toolbar);
        setupObserver(pagerViewModel);
        setupPageView(pagerActivityBinding.listViewpager);
    }

    private void initDataBinding() {
        pagerActivityBinding = DataBindingUtil.setContentView(this, R.layout.pager_activity);
        pagerViewModel = new PagerViewModel(this);
        pagerActivityBinding.setPagerViewModel(pagerViewModel);
    }

    private void setupPageView(ViewPager pager) {
        PeopleTabsAdapter adapter = new PeopleTabsAdapter(getSupportFragmentManager());
        adapter.addFragment(new TracksFragment(), "Tracks");
        pager.setAdapter(adapter);
        pagerActivityBinding.slidingTabs.setupWithViewPager(pager);

    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        pagerViewModel.reset();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_github) {
            startActivityActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PeopleFactory.PROJECT_URL)));
    }

    @Override
    public void update(Observable observable, Object arg) {
       if (observable instanceof PagerViewModel) {
            //PeopleAdapter peopleAdapter = (PeopleAdapter) pagerActivityBinding..getAdapter();
            //PagerViewModel peopleViewModel = (PagerViewModel) observable;
            //peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
       }
    }
}
