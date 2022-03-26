package com.codingstuff.nestedrealtimedb.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codingstuff.nestedrealtimedb.Model.ParentItem;
import com.codingstuff.nestedrealtimedb.R;

import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> {

    private List<ParentItem> parentItemList;

    public void setParentItemList(List<ParentItem> parentItemList){
        this.parentItemList = parentItemList;
    }
    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_parent_item , parent , false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {

        ParentItem parentItem = parentItemList.get(position);
        holder.parentName.setText(parentItem.getParentName());
        Glide.with(holder.itemView.getContext()).load(parentItem.getParentImage())
                .into(holder.parentIv);

        holder.childRecyclerView.setHasFixedSize(true);
        holder.childRecyclerView.setLayoutManager(new GridLayoutManager(holder.itemView.getContext() , 4));
        ChildAdapter childAdapter = new ChildAdapter();
        childAdapter.setChildItemList(parentItem.getChildItemList());
        holder.childRecyclerView.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (parentItemList != null){
            return parentItemList.size();
        }else{
            return 0;
        }

    }

    public class ParentViewHolder extends RecyclerView.ViewHolder{
        private TextView parentName;
        private ImageView parentIv;
        private RecyclerView childRecyclerView;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            parentName = itemView.findViewById(R.id.eachParentName);
            parentIv = itemView.findViewById(R.id.eachParentIV);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
        }
    }
}
