package com.ualr.recyclerviewassignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.adapters.RecyclerAdapter;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.model.InboxViewModel;

import java.util.List;

public class InboxListFragment extends Fragment implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = InboxListFragment.class.getSimpleName();
    private static final int DEFAULT_POS = 0;

    private Context mContext;
    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;
    private FloatingActionButton mFAB;
    private int selectedEmailPosition = -1;

    private InboxViewModel model;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inbox_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        List<Inbox> inboxList = DataGenerator.getInboxData(mContext);
        InboxViewModel mViewModel = new InboxViewModel(inboxList);

        mAdapter = new RecyclerAdapter(mContext, mViewModel.getInboxList().getValue());
        mAdapter.setOnItemClickListener(this);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(View view, Inbox obj, int position) {
        mAdapter.clearAllSelections();
        obj.toggleSelection();
        selectedEmailPosition = position;
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onIconClick(View view, Inbox obj, int position) {
        // Since we are not deleting with icon click anymore, this works the same as clicking the item
        mAdapter.clearAllSelections();
        obj.toggleSelection();
        selectedEmailPosition = position;
        mAdapter.notifyItemChanged(position);
    }

    public void addEmail() {
        Inbox newItem = DataGenerator.getRandomInboxItem(mContext);
        mAdapter.addEmail(DEFAULT_POS, newItem);
        recyclerView.scrollToPosition(DEFAULT_POS);
    }

    public Inbox getSelected() {
        return mAdapter.getSelectedItem();
    }

    public boolean deleteEmail() {
        if (selectedEmailPosition != -1) {
            mAdapter.deleteEmail(selectedEmailPosition);
            mAdapter.notifyItemRemoved(selectedEmailPosition);
            mAdapter.clearAllSelections();

            selectedEmailPosition = -1;
            return true;
        }
        return false;
    }



}
