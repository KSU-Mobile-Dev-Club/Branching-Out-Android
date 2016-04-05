package com.teioh08.branchingout.UI.Main.Presenter;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.teioh08.branchingout.GMapV2Direction;
import com.teioh08.branchingout.R;
import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.View.Fragments.FMapFragment;
import com.teioh08.branchingout.UI.Main.View.Mapper.FMapMap;

import java.util.ArrayList;

import rx.Subscription;

public class FMapPresenterImpl implements FMapPresenter, OnMapReadyCallback {
    final public static String TAG = FMapPresenterImpl.class.getSimpleName();

    public FMapMap mFMapMap;
    private GoogleMap mGoogleMap;
    private ArrayList<MarkerOptions> mMarkerOptionsList;
    private ArrayList<Marker> mMarkerList;
    private ArrayList<Tree> mTreeList;

    private boolean mMapPermission;
    private Subscription subscription, sub;

    public FMapPresenterImpl(FMapMap map) {
        mFMapMap = map;
        mMapPermission = false;
    }

    @Override
    public void init(Bundle bundle) {
        ((SupportMapFragment) ((FMapFragment) mFMapMap).getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
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
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }

        if (sub != null) {
            sub.unsubscribe();
            sub = null;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mGoogleMap.getUiSettings().setScrollGesturesEnabled(true);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setMapToolbarEnabled(true);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(39.189930, -96.581883)).zoom(17).build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        setupCameraListener();
        addMarkers();
        if (mMapPermission) mGoogleMap.setMyLocationEnabled(true);

    }

    @Override
    public void setPathToMarker(Tree tree) {
        // might just have to do a straight line to give user an idea of direction, not sure we can  path using campus sidewalks
        if (mMapPermission) {
            GMapV2Direction md = new GMapV2Direction();
            if (mGoogleMap.getMyLocation() != null) {
                sub = md.getDocument(new LatLng(mGoogleMap.getMyLocation().getLatitude(), mGoogleMap.getMyLocation().getLongitude()), new LatLng(39.189930, -96.581883), GMapV2Direction.MODE_WALKING)
                        .subscribe(document -> {
                            ArrayList<LatLng> directionPoint = md.getDirection(document);
                            PolylineOptions rectLine = new PolylineOptions().width(7).color(
                                    Color.BLUE);

                            for (int i = 0; i < directionPoint.size(); i++) {
                                rectLine.add(directionPoint.get(i));
                            }
                            Polyline polylin = mGoogleMap.addPolyline(rectLine);
                            sub = null;
                        });
            }
        }
    }

    @Override
    public void onLocationPermission() {
        mMapPermission = true;
        if (mGoogleMap != null)
            mGoogleMap.setMyLocationEnabled(true);
    }


    private void setupCameraListener() {
        //http://mondeca.com/index.php/en/any-place-en
        LatLng NORTHEAST = new LatLng(39.19683, -96.57678);
        LatLng SOUTHWEST = new LatLng(39.18626, -96.58519);
        LatLngBounds BOUNDS = new LatLngBounds(SOUTHWEST, NORTHEAST);

        mGoogleMap.setOnCameraChangeListener(position ->
        {
            if (BOUNDS.contains(mGoogleMap.getCameraPosition().target)) {
                return;
            }

            double x = mGoogleMap.getCameraPosition().target.longitude;
            double y = mGoogleMap.getCameraPosition().target.latitude;

            double maxX = BOUNDS.northeast.longitude;
            double maxY = BOUNDS.northeast.latitude;
            double minX = BOUNDS.southwest.longitude;
            double minY = BOUNDS.southwest.latitude;

            if (x < minX) {
                x = minX;
            }
            if (x > maxX) {
                x = maxX;
            }
            if (y < minY) {
                y = minY;
            }
            if (y > maxY) {
                y = maxY;
            }

            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(y, x)));
        });
    }

    private void addMarkers() {
        // create marker
        mMarkerOptionsList = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("trees");
        query.findInBackground((objects, e) -> {
            mMarkerOptionsList = new ArrayList<MarkerOptions>();
            mTreeList = new ArrayList<Tree>();

            for (ParseObject o : objects) {
                mTreeList.add(new Tree(o.getString("common"), o.getString("scientific"), o.getObjectId(), o.getParseGeoPoint("cord").getLatitude(), o.getParseGeoPoint("cord").getLatitude(), o.getInt("treeId"), o.getString("wiki"), "o.getString(image)"));
                double lat = o.getParseGeoPoint("cord").getLatitude();
                double log = o.getParseGeoPoint("cord").getLongitude();
                String title = o.getString("common");
                MarkerOptions marker = new MarkerOptions()
                        .position(new LatLng(lat, log))
                        .title(title)
                        .snippet("");
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));


                mMarkerOptionsList.add(marker);
            }

            if (mMarkerOptionsList.size() > 0) {
                mMarkerList = new ArrayList<>();
                for (MarkerOptions m : mMarkerOptionsList) {
                    mMarkerList.add(mGoogleMap.addMarker(m));
                }
            }
        });

    }
}
