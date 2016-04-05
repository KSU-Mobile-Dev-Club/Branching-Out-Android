package com.teioh08.branchingout.UI.Main.Presenter;

import android.os.Bundle;

import com.teioh08.branchingout.UI.Main.View.Mapper.FGalleryMap;


public class FGalleryPresenterImpl implements FGalleryPresenter {
    final public static String TAG = FGalleryPresenterImpl.class.getSimpleName();

    public FGalleryMap mFGalleryMap;

    public FGalleryPresenterImpl(FGalleryMap map) {
        mFGalleryMap = map;
    }

    @Override
    public void init(Bundle bundle) {

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
}
