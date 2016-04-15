package com.teioh08.branchingout.UI.Main.View.Fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.teioh08.branchingout.R;
import com.teioh08.branchingout.UI.Main.Presenter.FIntroPresenter;
import com.teioh08.branchingout.UI.Main.Presenter.FIntroPresenterImpl;
import com.teioh08.branchingout.UI.Main.View.Mapper.FIntroMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FIntroFragment extends Fragment implements FIntroMap {
    public final static String TAG = FIntroFragment.class.getSimpleName();

    @Bind(R.id.info_image) ImageView mInfoImage;

    private FIntroPresenter mFIntroPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_intro_layout, container, false);
        ButterKnife.bind(this, v);

        mFIntroPresenter = new FIntroPresenterImpl(this);
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
