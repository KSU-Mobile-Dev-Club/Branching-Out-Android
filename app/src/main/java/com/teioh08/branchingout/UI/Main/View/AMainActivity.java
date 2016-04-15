package com.teioh08.branchingout.UI.Main.View;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.teioh08.branchingout.R;
import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.Adapters.ViewPagerAdapter;
import com.teioh08.branchingout.UI.Main.Presenter.AMainPresenter;
import com.teioh08.branchingout.UI.Main.Presenter.AMainPresenterImpl;
import com.teioh08.branchingout.UI.Main.View.Fragments.FTreeInfoFragment;
import com.teioh08.branchingout.UI.Main.View.Mapper.AMainMap;
import com.teioh08.branchingout.UI.Main.View.Widgets.SlidingTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AMainActivity extends AppCompatActivity implements AMainMap {
    final public static String TAG = AMainActivity.class.getSimpleName();

    private AMainPresenter mAMainPresenter;

    @Bind(R.id.pager) ViewPager mViewPager;
    @Bind(R.id.tabs) SlidingTabLayout tabs;
    //    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
//    @Bind(R.id.drawerLayoutListView) ListView mDrawerListView;
    @Bind(R.id.toolbar) Toolbar mToolBar;
    @Bind(R.id.activityTitle) TextView mActivityTitle;
    @Bind(R.id.directionButton) ImageButton mDirectionButton;

    private Toast mExitToast;
    private Fragment mTreeInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAMainPresenter = new AMainPresenterImpl(this);

        if (savedInstanceState != null) {
            mAMainPresenter.onRestoreState(savedInstanceState);
            if(savedInstanceState.containsKey(FTreeInfoFragment.TAG))mTreeInfoFragment = FTreeInfoFragment.getNewInstance(null);
        }
        mAMainPresenter.init(getIntent().getExtras());
        mExitToast = Toast.makeText(this, "Press back again to exit!", Toast.LENGTH_SHORT);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mTreeInfoFragment != null) outState.putString(FTreeInfoFragment.TAG, null);
        mAMainPresenter.onSavedState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true; //to show overflow
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setupTabLayout() {
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(position -> getResources().getColor(R.color.tabsScrollColor));
    }

    @Override
    public void onDrawerClose() {

    }

    @Override
    public void onDrawerOpen() {

    }

    @Override
    public void registerAdapter(ViewPagerAdapter adapter) {
        if (adapter != null) {
            mViewPager.setAdapter(adapter);
            mViewPager.setOffscreenPageLimit(4);
            tabs.setViewPager(mViewPager);
        }
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void changeActivityTitle(String title) {
        mActivityTitle.setText(title);
    }

    @Override
    public void setPathToTree(Tree tree) {
        mAMainPresenter.setPathToMarker(tree);
    }

    @Override
    public void goToMap() {
        onBackPressed();
        mViewPager.setCurrentItem(2);
        mAMainPresenter.setPathToMarker();
    }

    @Override
    public void startTreeInfoFragment(Tree tree) {
        mTreeInfoFragment = FTreeInfoFragment.getNewInstance(tree);
        getSupportFragmentManager().beginTransaction().addToBackStack(FTreeInfoFragment.TAG).add(android.R.id.content, mTreeInfoFragment, FTreeInfoFragment.TAG).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mAMainPresenter.onLocationPermission();
        }
    }

    @Override
    public void onBackPressed() {
        if (mTreeInfoFragment != null) {
            mTreeInfoFragment = null;
            super.onBackPressed();
        } else if (!mExitToast.getView().isShown()) {
            mExitToast.show();
        } else {
            super.onBackPressed();
        }
    }
}
