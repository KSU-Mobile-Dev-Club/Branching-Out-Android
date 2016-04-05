package com.teioh08.branchingout.UI.Main.Presenter;

import android.os.Bundle;

import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeInfoFragment;
import com.teioh08.branchingout.UI.Main.View.Mapper.FTreeInfoMap;

public class FTreeInfoPresenterImpl implements FTreeInfoPresenter{
    final public static String TAG = FTreeInfoPresenterImpl.class.getSimpleName();

    public FTreeInfoMap mFTreeInfoMap;
    private Tree tree;

    public FTreeInfoPresenterImpl(FTreeInfoMap map){
        mFTreeInfoMap = map;
    }

    @Override
    public void init(Bundle bundle) {
        tree = bundle.getParcelable(FTreeInfoFragment.TREE_ARGUMENT_KEY);
        mFTreeInfoMap.changeTitle(tree.common);
        mFTreeInfoMap.setupToolBar();
        mFTreeInfoMap.setupWebView(tree);
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
        mFTreeInfoMap.changeTitle("Branching Out");
    }

    @Override
    public Tree onDirectionButtonClicked(){
        return tree;
    }
}
