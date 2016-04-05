package com.teioh08.branchingout.UI.Main.View.Fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.teioh08.branchingout.R;
import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.Presenter.FTreeInfoPresenter;
import com.teioh08.branchingout.UI.Main.Presenter.FTreeInfoPresenterImpl;
import com.teioh08.branchingout.UI.Main.View.Mapper.FTreeInfoMap;
import com.teioh08.branchingout.UI.Main.View.Mapper.Listeners;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FTreeInfoFragment extends Fragment implements FTreeInfoMap {
    public final static String TAG = FTreeInfoFragment.class.getSimpleName();
    public final static String TREE_ARGUMENT_KEY = "TREE_ARGUMENT_KEY";

    @Bind(R.id.tree_webview) WebView mWebView;
    @Bind(R.id.toolbar) Toolbar mToolBar;
    @Bind(R.id.directionButton) ImageButton mDirectionButton;

    private FTreeInfoPresenter mFTreeInfoPresenter;
    private Listeners.TreeInfoListener listener;

    public static FTreeInfoFragment getNewInstance(Tree tree) {
        FTreeInfoFragment newFragment = new FTreeInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(TREE_ARGUMENT_KEY, tree);
        newFragment.setArguments(bundle);

        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tree_info_layout, container, false);
        ButterKnife.bind(this, v);

        mFTreeInfoPresenter = new FTreeInfoPresenterImpl(this);
        mFTreeInfoPresenter.init(getArguments());
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mFTreeInfoPresenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setupWebView(Tree tree) {
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl(tree.wiki);
    }

    @Override
    public void setupToolBar() {
        if (mDirectionButton.getVisibility() == View.GONE) {
            mDirectionButton.setVisibility(View.VISIBLE);
        }

        mToolBar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        mToolBar.setNavigationOnClickListener(v -> listener.onBackPressed());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listeners.TreeInfoListener)
            listener = (Listeners.TreeInfoListener) context;
        else
            throw new ClassCastException(context.toString() + " must implement Listeners.TreeInfoListener");
    }

    @Override
    public void changeTitle(String title) {
        listener.changeActivityTitle(title);
    }

    @OnClick(R.id.directionButton)
    void onDirectionButtonClicked() {
        listener.setPathToTree(mFTreeInfoPresenter.onDirectionButtonClicked());
        VerifyDialog.getNewInstance().show(getActivity().getSupportFragmentManager(), VerifyDialog.TAG);
    }

    private class MyWebViewClient extends WebViewClient {
        //Webview client to prevent loading webpage out of app
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
