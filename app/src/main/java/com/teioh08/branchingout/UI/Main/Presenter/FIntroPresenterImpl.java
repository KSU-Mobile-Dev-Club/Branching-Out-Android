package com.teioh08.branchingout.UI.Main.Presenter;

import android.os.Bundle;

import com.teioh08.branchingout.UI.Main.View.Mapper.FIntroMap;


public class FIntroPresenterImpl implements FIntroPresenter{
    final public static String TAG = FIntroPresenterImpl.class.getSimpleName();

    public FIntroMap mFIntroMap;

    public FIntroPresenterImpl(FIntroMap map){
        mFIntroMap = map;
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
