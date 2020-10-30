package com.ualr.recyclerviewassignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.adapters.RecyclerAdapter;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

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
        mAdapter.clearAllSelections();
        obj.toggleSelection();
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onIconClick(View view, Inbox obj, int position) {
        if (obj.isSelected()) {
            mAdapter.deleteEmail(position);
        }
    }
}