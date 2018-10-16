package com.dev.android.lastf.view.Activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.dev.android.lastf.R;
import com.dev.android.lastf.data.LastConstants;
import com.dev.android.lastf.databinding.PagerActivityBinding;
import com.dev.android.lastf.model.Album;
import com.dev.android.lastf.model.Track;
import com.dev.android.lastf.view.Adapters.TabsAdapter;
import com.dev.android.lastf.view.Fragments.AlbumFragment;
import com.dev.android.lastf.view.Fragments.ArtistFragment;
import com.dev.android.lastf.view.Fragments.TracksFragment;
import com.dev.android.lastf.viewmodel.Views.PagerViewModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PagerActivity extends BaseActivity implements Observer, ViewPager.OnPageChangeListener{

    private PagerActivityBinding pagerBinding; // Data binding
    private PagerViewModel pagerVM;    // ViewModel

    private TracksFragment tracksFragment;
    private AlbumFragment albumFragment;
    private ArtistFragment artistFragment;
    public static String searchText = "";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         initDataBinding();
         setTheme(R.style.AppTheme);
         setToolbar();
         setupObserver(pagerVM);
         setStatusBarColor(R.color.colorPrimary);
    }

    private void setToolbar(){
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.lastfm_icon_name);
        pagerBinding.toolbar.setNavigationIcon(drawable);
        setSupportActionBar(pagerBinding.toolbar);
        setTitle("");
    }

    private void initDataBinding() {
        pagerBinding = DataBindingUtil.setContentView(this, R.layout.pager_activity);
        pagerVM = new PagerViewModel(this);
        pagerBinding.setPagerViewModel(pagerVM);
        pagerBinding.getPagerViewModel().pagerSearchView.set(View.GONE);
        setupPageView(pagerBinding.listViewpager, pagerBinding.slidingTabs);
    }

    private void setupPageView(ViewPager pager, TabLayout tabs) {
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());

        tracksFragment = new TracksFragment();
        albumFragment  = new AlbumFragment();
        artistFragment = new ArtistFragment();

        adapter.addFragment(tracksFragment, LastConstants.LABEL_TOP_TRACKS);
        adapter.addFragment(albumFragment,  LastConstants.LABEL_TOP_ALBUMS);
        adapter.addFragment(artistFragment, LastConstants.LABEL_TOP_ARTISTS);

        pager.setOffscreenPageLimit(LastConstants.LIMIT_PAGE_VIEW_PAGER);
        pager.setOnPageChangeListener(this);
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }

    private void setupSearchView(MenuItem item){

        pagerBinding.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText = newText;
                if(pagerBinding.getPagerViewModel().getPagerCurrent() == LastConstants.TRACKS_PAGE){
                    List<Track> allTracks = tracksFragment.getVM().getAllTracksList();
                    tracksFragment.getVM().setTrackSearchs(pagerVM.resultTracks(searchText,allTracks));
                }
                if(pagerBinding.getPagerViewModel().getPagerCurrent() == LastConstants.ALBUMS_PAGE){
                    List<Album> allAlbums = albumFragment.getVM().getAllAlbumslist();
                    albumFragment.getVM().setAlbumSearchs(pagerVM.resultAlbums(searchText,allAlbums));
                }
                return false;
            }
        });

        pagerBinding.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                pagerVM.pagerSearchView.set(View.VISIBLE);
                pagerBinding.appBarLayout.setExpanded(false, true);
            }

            @Override
            public void onSearchViewClosed() {

            }
        });
        pagerBinding.searchView.setMenuItem(item);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        pagerVM.reset();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        setupSearchView(menu.getItem(0));
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_github) {
            startActivityActionView();
            return true;
        }

        if (item.getItemId() == R.id.menu_search) {
            pagerVM.pagerSearchView.set(View.VISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(LastConstants.PROJECT_URL)));
    }

    @Override
    public void update(Observable observable, Object arg) {
       if (observable instanceof PagerViewModel) {
          //  PagerViewModel peopleViewModel = (PagerViewModel) observable;
          //  peopleViewModel.notifyObservers();
       }
    }


    /// OVERRIDAE PAGESVIEWS
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageSelected(int position) {
        pagerVM.setPagerCurrent(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) { }
}
