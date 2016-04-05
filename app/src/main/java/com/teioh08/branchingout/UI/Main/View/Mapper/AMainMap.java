package com.teioh08.branchingout.UI.Main.View.Mapper;

import android.content.Context;

import com.teioh08.branchingout.UI.Main.Adapters.ViewPagerAdapter;
import com.teioh08.branchingout.UI.Main.View.Fragments.FMapFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeFragment;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeInfoFragment;

public interface AMainMap extends Listeners.VerifyDialogListener, Listeners.TreeInfoListener, Listeners.TreeListener {
    Context getContext();

    void setupToolbar();

    void setupTabLayout();

    void onDrawerClose();

    void onDrawerOpen();

    void registerAdapter(ViewPagerAdapter adapter);

}
