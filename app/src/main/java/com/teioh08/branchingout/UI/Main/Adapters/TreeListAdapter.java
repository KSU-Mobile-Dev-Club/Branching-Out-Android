package com.teioh08.branchingout.UI.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teioh08.branchingout.R;
import com.teioh08.branchingout.Tree;

import java.util.ArrayList;
import java.util.List;


public class TreeListAdapter extends RecyclerView.Adapter<TreeListAdapter.ViewHolder> {

    private final List<Tree> mItems = new ArrayList<>();
    private final Context mContext;
    private final ItemSelectedListener mListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView title;
        public final ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tree_name);
            image = (ImageView) itemView.findViewById(R.id.tree_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(getLayoutPosition());
            mListener.onItemSelected(v, mItems.get(getAdapterPosition()));
        }
    }

    public interface ItemSelectedListener {
        void onItemSelected(View itemView, Tree item);
    }

    public TreeListAdapter(Context context, ItemSelectedListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void clearData() {
        mItems.clear();
    }

    public void addData(List<Tree> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void removeSingleData(int pos) {
        if (mItems.size() > 0) {
            mItems.remove(pos);
            notifyDataSetChanged();
        }
    }

    public Tree getTree(int pos) {
        if (mItems.size() == 0) return null;
        return mItems.get(pos);
    }

    public List<Tree> getTrees() {
        return mItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tree_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tree item = mItems.get(position);
        holder.title.setText(item.common);

        //get images
        Glide.with(mContext).load(item.image).skipMemoryCache(true).into(holder.image);

//        Glide.with(mContext).load("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSTyXS732AWVGDYm3M62nRZcVexPoEVW9rwPkCYsZozn8mG042EPw").skipMemoryCache(true).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}