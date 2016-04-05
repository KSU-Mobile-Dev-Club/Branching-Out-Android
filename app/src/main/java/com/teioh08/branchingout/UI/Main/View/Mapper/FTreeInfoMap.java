package com.teioh08.branchingout.UI.Main.View.Mapper;

import android.content.Context;

import com.teioh08.branchingout.Tree;

public interface FTreeInfoMap {
    Context getContext();

    void changeTitle(String title);

    void setupWebView(Tree tree);

    void setupToolBar();
}
