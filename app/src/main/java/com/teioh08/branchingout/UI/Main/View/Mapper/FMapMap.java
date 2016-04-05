package com.teioh08.branchingout.UI.Main.View.Mapper;

import android.content.Context;

import com.teioh08.branchingout.Tree;

public interface FMapMap {
    Context getContext();

    void setPathToMarker(Tree tree);

    void onLocationPermission();
}
