package com.teioh08.branchingout.UI.Main.View.Fragments;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teioh08.branchingout.R;
import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.Adapters.TreeListAdapter;
import com.teioh08.branchingout.UI.Main.Presenter.FTreePresenter;
import com.teioh08.branchingout.UI.Main.Presenter.FTreePresenterImpl;
import com.teioh08.branchingout.UI.Main.View.Mapper.FTreeMap;
import com.teioh08.branchingout.UI.Main.View.Mapper.Listeners;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FTreeFragment extends Fragment implements FTreeMap{
    public final static String TAG = FTreeFragment.class.getSimpleName();

    @Bind(R.id.tree_recycler_view) RecyclerView mTreeList;
    private FTreePresenter mFTreePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tree_layout, container, false);
        ButterKnife.bind(this, v);

        mFTreePresenter = new FTreePresenterImpl(this);
        mFTreePresenter.init(getArguments());
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
    public void setupRecyclerView(TreeListAdapter adapter, RecyclerView.LayoutManager manager) {
        mTreeList.setAdapter(adapter);
        mTreeList.setLayoutManager(manager);
        mTreeList.addItemDecoration(new SpacesItemDecoration(4));
    }

    @Override
    public void onTreeSelected(Tree item) {
        listener.startTreeInfoFragment(item);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int halfSpace;

        public SpacesItemDecoration(int space) {
            this.halfSpace = space / 2;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getPaddingLeft() != halfSpace) {
                parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace);
                parent.setClipToPadding(false);
            }

            outRect.top = halfSpace;
            outRect.bottom = halfSpace;
            outRect.left = halfSpace;
            outRect.right = halfSpace;
        }
    }

    //Interface - Fragment communicating with activity
    private Listeners.TreeListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if (context instanceof Listeners.TreeListener) listener = (Listeners.TreeListener) context;
        else throw new ClassCastException(context.toString() + " must implement Listeners.TreeInfoListener");
    }
}
