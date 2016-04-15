package com.teioh08.branchingout.UI.Main.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.teioh08.branchingout.R;
import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.Adapters.TreeListAdapter;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeInfoFragment;
import com.teioh08.branchingout.UI.Main.View.Mapper.FTreeMap;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class FTreePresenterImpl implements FTreePresenter {
    final public static String TAG = FTreePresenterImpl.class.getSimpleName();

    private FTreeMap mFTreeMap;
    private TreeListAdapter mAdapter;
    LinearLayoutManager mLinearLayoutManager;
    GridLayoutManager mGridLayoutManager;
    Subscription subscription;
    List<Tree> fake;

    public FTreePresenterImpl(FTreeMap map) {
        mFTreeMap = map;
    }

    @Override
    public void init(Bundle bundle) {
        mAdapter = new TreeListAdapter(mFTreeMap.getContext(), (itemView, item) -> mFTreeMap.onTreeSelected(item));
        mLinearLayoutManager = new LinearLayoutManager(mFTreeMap.getContext());
        mGridLayoutManager = new GridLayoutManager(mFTreeMap.getContext(), 3);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("trees");

        query.findInBackground((objects, e) -> {
                    fake = new ArrayList<>();
                    if (objects != null) {
                        for (ParseObject o : objects) {
                            fake.add(new Tree(o.getString("common"), o.getString("scientific"), o.getObjectId(), o.getParseGeoPoint("cord").getLatitude(), o.getParseGeoPoint("cord").getLatitude(), o.getInt("treeId"), o.getString("wiki"), o.getString("photo")));
//                            fake.add(new Tree(o.getString("common"), o.getString("scientific"), o.getObjectId(), o.getInt("treeId"), o.getString("wiki"), "o.getString(image)"));
                        }
                    }
                    mAdapter.addData(fake);
                    mFTreeMap.setupRecyclerView(mAdapter, mGridLayoutManager);
                }
        );
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
