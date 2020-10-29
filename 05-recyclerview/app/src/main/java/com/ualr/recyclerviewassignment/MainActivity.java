package com.ualr.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.adapters.RecyclerAdapter;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

// TODO 05. Create a new Adapter class and the corresponding ViewHolder class in a different file. The adapter will be used to populate
//  the recyclerView and manage the interaction with the items in the list
// TODO 06. Detect click events on the list items. Implement a new method to toggle items' selection in response to click events
// TODO 07. Detect click events on the thumbnail located on the left of every list row when the corresponding item is selected.
//  Implement a new method to delete the corresponding item in the list
// TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator class to create the new item to be added.

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int DEFAULT_POS = 0;
    private RecyclerAdapter mAdapter;
    private FloatingActionButton mFAB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_multi_selection);
        initComponent();
    }

    private void initComponent() {
        List<Inbox> inboxList = DataGenerator.getInboxData(this);
        inboxList.addAll(DataGenerator.getInboxData(this));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAdapter(this, inboxList);
        mAdapter.setOnItemClickListener(this);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inbox newItem = DataGenerator.getRandomInboxItem(getApplicationContext());
                mAdapter.addEmail(DEFAULT_POS, newItem);
                recyclerView.scrollToPosition(DEFAULT_POS);
            }
        });


    }

    @Override
    public void onItemClick(View view, Inbox obj, int position) {
        Log.d(TAG, String.format("Clicked on %s row", obj.getFrom()));
    }

    @Override
    public void onIconClick(View view, Inbox obj, int position) {
        Log.d(TAG, String.format("Clicked on %s icon", obj.getFrom()));
    }
}