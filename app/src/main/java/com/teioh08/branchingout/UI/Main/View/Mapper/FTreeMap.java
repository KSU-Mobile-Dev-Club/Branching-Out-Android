package com.teioh08.branchingout.UI.Main.View.Mapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.teioh08.branchingout.Tree;
import com.teioh08.branchingout.UI.Main.Adapters.TreeListAdapter;

public interface FTreeMap {
    Context getContext();

    void setupRecyclerView(TreeListAdapter adapter, RecyclerView.LayoutManager manager);

    void onTreeSelected(Tree item);
}
