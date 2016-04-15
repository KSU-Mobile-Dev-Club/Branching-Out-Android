package com.teioh08.branchingout.UI.Main.Presenter;

import com.teioh08.branchingout.Maps.LifeCycleMap;
import com.teioh08.branchingout.Tree;


public interface AMainPresenter extends LifeCycleMap {
    void setPathToMarker(Tree tree);

    void setPathToMarker();

    void onLocationPermission();

}

