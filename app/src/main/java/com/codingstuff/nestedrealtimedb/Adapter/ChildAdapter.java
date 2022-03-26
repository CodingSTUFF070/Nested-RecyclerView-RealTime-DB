package com.codingstuff.nestedrealtimedb.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codingstuff.nestedrealtimedb.Model.ChildItem;
import com.codingstuff.nestedrealtimedb.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    private List<ChildItem> childItemList;
    public void setChildItemList(List<ChildItem> childItemList){
        this.childItemList = childItemList;

        this.childItemList.removeAll(Collections.singleton(null));
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_child_item , parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {

        ChildItem childItem = childItemList.get(position);
        holder.childName.setText(childItem.getChildName());
        Glide.with(holder.itemView.getContext()).load(childItem.getChildImage())
                .into(holder.childImageView);
    }

    @Override
    public int getItemCount() {
        if (childItemList != null){
            return childItemList.size();
        }else{
            return  0;
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder{
        private TextView childName;
        private ImageView childImageView;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);

            childName = itemView.findViewById(R.id.eachChildItemName);
            childImageView = itemView.findViewById(R.id.eachChildItemIV);
        }
    }
}
