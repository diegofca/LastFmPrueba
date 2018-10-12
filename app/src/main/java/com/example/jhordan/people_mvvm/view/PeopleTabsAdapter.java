package com.example.jhordan.people_mvvm.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PeopleTabsAdapter extends FragmentPagerAdapter {

    public List<Fragment> pageFragments = new ArrayList<>();
    public List<String> pageTittles = new ArrayList<>();

    public void addFragment(Fragment fragment, String tittle){
        pageFragments.add(fragment);
        pageTittles.add(tittle);
    }

    public void addAll(List<Fragment>  fragments, List<String> tittles){
        pageFragments.addAll(fragments);
        pageTittles.addAll(tittles);
    }

    public PeopleTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pageFragments.get(position);
    }

    @Override
    public int getCount() {
        return pageFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTittles.get(position);
    }

}
