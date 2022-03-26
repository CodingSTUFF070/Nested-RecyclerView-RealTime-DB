package com.codingstuff.nestedrealtimedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.codingstuff.nestedrealtimedb.Adapter.ParentAdapter;
import com.codingstuff.nestedrealtimedb.Model.ParentItem;
import com.codingstuff.nestedrealtimedb.viewmodel.FirebaseViewModel;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView parentRecyclerView;
    private FirebaseViewModel firebaseViewModel;
    private ParentAdapter parentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentRecyclerView = findViewById(R.id.parentRecyclerView);

        parentRecyclerView.setHasFixedSize(true);
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parentAdapter = new ParentAdapter();
        parentRecyclerView.setAdapter(parentAdapter);

        firebaseViewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);

        firebaseViewModel.getAllData();
        firebaseViewModel.getParentItemMutableLiveData().observe(this, new Observer<List<ParentItem>>() {
            @Override
            public void onChanged(List<ParentItem> parentItemList) {
                parentAdapter.setParentItemList(parentItemList);
                parentAdapter.notifyDataSetChanged();
            }
        });
        firebaseViewModel.getDatabaseErrorMutableLiveData().observe(this, new Observer<DatabaseError>() {
            @Override
            public void onChanged(DatabaseError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}