package com.codingstuff.nestedrealtimedb.repo;


import android.util.Log;

import androidx.annotation.NonNull;

import com.codingstuff.nestedrealtimedb.Model.ChildItem;
import com.codingstuff.nestedrealtimedb.Model.ParentItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepo {
    private DatabaseReference databaseReference;
    private OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;

    public FirebaseRepo(OnRealtimeDbTaskComplete onRealtimeDbTaskComplete){
        this.onRealtimeDbTaskComplete = onRealtimeDbTaskComplete;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("It Industry");
    }

    public void getAllData(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ParentItem> parentItemList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()){

                    ParentItem parentItem = new ParentItem();
                    parentItem.setParentName(ds.child("parentName").getValue(String.class));
                    parentItem.setParentImage(ds.child("parentImage").getValue(String.class));

                    GenericTypeIndicator<ArrayList<ChildItem>> genericTypeIndicator =
                            new GenericTypeIndicator<ArrayList<ChildItem>>() {};

                    parentItem.setChildItemList(ds.child("childData").getValue(genericTypeIndicator));
                    parentItemList.add(parentItem);
                }
                Log.d("TAG", "onDataChange: "+parentItemList);
                onRealtimeDbTaskComplete.onSuccess(parentItemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFailure(error);
            }
        });
    }

    public interface OnRealtimeDbTaskComplete{
        void onSuccess(List<ParentItem> parentItemList);
        void onFailure(DatabaseError error);
    }
}
