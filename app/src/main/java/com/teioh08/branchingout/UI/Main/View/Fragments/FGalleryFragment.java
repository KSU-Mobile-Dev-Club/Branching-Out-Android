package com.teioh08.branchingout.UI.Main.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teioh08.branchingout.R;
import com.teioh08.branchingout.UI.Main.Presenter.FGalleryPresenter;
import com.teioh08.branchingout.UI.Main.Presenter.FGalleryPresenterImpl;
import com.teioh08.branchingout.UI.Main.View.Mapper.FGalleryMap;

import butterknife.ButterKnife;


public class FGalleryFragment extends Fragment implements FGalleryMap{
    public final static String TAG = FGalleryFragment.class.getSimpleName();

    private FGalleryPresenter mFGalleryPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery_layout, container, false);
        ButterKnife.bind(this, v);

        mFGalleryPresenter = new FGalleryPresenterImpl(this);
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


}
