package com.teioh08.branchingout.UI.Main.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.teioh08.branchingout.UI.Main.View.Fragments.FGalleryFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FIntroFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FMapFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) { // if the position is 0 we are returning the First tab
            Fragment f = new FIntroFragment();
            registeredFragments.put(0, f);
            return f;
        } else if (position == 1) {
            Fragment f = new FTreeFragment();
            registeredFragments.put(1, f);
            return f;
        } else { //(position == 2)
            Fragment f = new FMapFragment();
            registeredFragments.put(2, f);
            return f;
        }
//        else {
//            Fragment f = new FGalleryFragment();
//            registeredFragments.put(2, f);
//            return f;
//        }
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    public boolean hasRegisteredFragments(){
        return registeredFragments.get(0) != null;
    }
}
