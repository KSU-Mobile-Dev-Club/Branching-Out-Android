package com.teioh08.branchingout.UI.Main.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.Adapters.ViewPagerAdapter;
import com.teioh08.branchingout.UI.Main.View.AMainActivity;
import com.teioh08.branchingout.UI.Main.View.Fragments.FMapFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeInfoFragment;
import com.teioh08.branchingout.UI.Main.View.Mapper.AMainMap;


public class AMainPresenterImpl implements AMainPresenter {
    public final static String TAG = AMainPresenterImpl.class.getSimpleName();

    private final CharSequence mTabTitles[] = {"Info", "Tree", "Map", "Gallery"};
    private AMainMap mAMainMap;
    private ViewPagerAdapter mViewPagerAdapter;
    private Tree mTree;

    public AMainPresenterImpl(AMainMap map) {
        mAMainMap = map;
    }

    @Override
    public void init(Bundle bundle) {
        //init adapters
        mViewPagerAdapter = new ViewPagerAdapter(((FragmentActivity) mAMainMap.getContext()).getSupportFragmentManager(), mTabTitles, 3);

        //init layout
        mAMainMap.setupTabLayout();
        mAMainMap.registerAdapter(mViewPagerAdapter);
        mAMainMap.setupToolbar();
    }

    @Override
    public void onSavedState(Bundle bundle) {

    }

    @Override
    public void onRestoreState(Bundle bundle) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setPathToMarker(Tree tree) {
        mTree = tree;
//        ((FMapFragment) mViewPagerAdapter.getRegisteredFragment(2)).setPathToMarker(tree);
    }

    @Override
    public void setPathToMarker() {
        if(mTree != null) {
            ((FMapFragment) mViewPagerAdapter.getRegisteredFragment(2)).setPathToMarker(mTree);
        }
    }

    @Override
    public void onLocationPermission() {
        if(mViewPagerAdapter.hasRegisteredFragments())
            ((FMapFragment) mViewPagerAdapter.getRegisteredFragment(2)).onLocationPermission();
    }
}
