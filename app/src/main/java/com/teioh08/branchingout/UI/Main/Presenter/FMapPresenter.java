package com.teioh08.branchingout.UI.Main.Presenter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.teioh08.branchingout.Maps.LifeCycleMap;
import com.teioh08.branchingout.Tree;

public interface FMapPresenter extends LifeCycleMap {
    void onMapReady(GoogleMap googleMap);

    void setPathToMarker(Tree tree);

    void onLocationPermission();

}
