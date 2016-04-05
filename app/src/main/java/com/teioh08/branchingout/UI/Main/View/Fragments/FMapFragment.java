package com.teioh08.branchingout.UI.Main.View.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teioh08.branchingout.R;
import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.Presenter.FMapPresenter;
import com.teioh08.branchingout.UI.Main.Presenter.FMapPresenterImpl;
import com.teioh08.branchingout.UI.Main.View.Mapper.FMapMap;

import butterknife.ButterKnife;

//http://developer.android.com/guide/topics/location/strategies.html
public class FMapFragment extends Fragment implements FMapMap {
    public final static String TAG = FMapFragment.class.getSimpleName();

    private FMapPresenter mFMapPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_layout, container, false);
        ButterKnife.bind(this, v);

        mFMapPresenter = new FMapPresenterImpl(this);
        mFMapPresenter.init(getArguments());
        checkPermissions();

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setPathToMarker(Tree tree) {
        mFMapPresenter.setPathToMarker(tree);
    }

    @Override
    public void onLocationPermission() {
        mFMapPresenter.onLocationPermission();
    }

    private void checkPermissions(){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            onLocationPermission();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 50);
        }
    }
}
