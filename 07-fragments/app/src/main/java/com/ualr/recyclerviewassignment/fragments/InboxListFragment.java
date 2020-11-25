package com.ualr.recyclerviewassignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
    private static final String FORWARD_TAG = ForwardFragment.class.getSimpleName();
    private static final String FORWARD_KEY = "FORWARD_EMAIL";

    private Context mContext;
    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;

    private InboxViewModel mViewModel;

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
        mViewModel = new ViewModelProvider(getActivity()).get(InboxViewModel.class);
        mViewModel.setInboxList(inboxList);

        mAdapter = new RecyclerAdapter(mContext, mViewModel.getInboxList().getValue());
        mAdapter.setOnItemClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        mViewModel.getInboxList().observe(getViewLifecycleOwner(), new Observer<List<Inbox>>() {
            @Override
            public void onChanged(List<Inbox> inboxList) {
                mAdapter.updateItems(inboxList);
            }
        });


    }

    @Override
    public void onItemClick(View view, Inbox obj, int position) {
        List<Inbox> currentData = mViewModel.getInboxList().getValue();
        clearAllSelections(currentData);
        currentData.get(position).toggleSelection();
        mViewModel.setSelectedIndex(position);
        mViewModel.setInboxList(currentData);
    }

    @Override
    public void onIconClick(View view, Inbox obj, int position) {
        // Since we are not deleting with icon click anymore, this works the same as clicking the item
        List<Inbox> currentData = mViewModel.getInboxList().getValue();
        clearAllSelections(currentData);
        currentData.get(position).toggleSelection();
        mViewModel.setSelectedIndex(position);
        mViewModel.setInboxList(currentData);
    }

    public void addEmail() {
        Inbox newItem = DataGenerator.getRandomInboxItem(mContext);
        List<Inbox> currentData = mViewModel.getInboxList().getValue();
        currentData.add(0, newItem);
        mViewModel.setInboxList(currentData);
    }

    public boolean deleteEmail() {
        int currentSelection = mViewModel.getSelectedIndex().getValue();
        List<Inbox> currentData = mViewModel.getInboxList().getValue();

        if (currentSelection != -1 && currentData != null) {
            currentData.remove(currentSelection);
            clearAllSelections(currentData);

            mViewModel.setInboxList(currentData);
            mViewModel.setSelectedIndex(-1);
            return true;
        }
        return false;
    }

    public int getSelectedEmailPosition() {
        return mViewModel.getSelectedIndex().getValue();
    }

    public void clearAllSelections(List<Inbox> inboxList) {
        for (Inbox inbox: inboxList) {
            inbox.setSelected(false);
        }
    }

    public void forwardEmail() {
        ForwardFragment forwardFragment = ForwardFragment.newInstance(getSelectedEmailPosition());
        forwardFragment.show(getParentFragmentManager(), TAG);
    }



}
