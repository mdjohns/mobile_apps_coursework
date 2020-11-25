package com.ualr.recyclerviewassignment.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.model.InboxViewModel;

import java.util.List;

public class ForwardFragment extends DialogFragment {
    private static final String TAG = ForwardFragment.class.getSimpleName();
    private static final String FORWARD_KEY = "FORWARD_EMAIL";
    private static final String SELECT_KEY = "selectedIndex";

    //private int selectedIndex;
    private InboxViewModel mViewModel;


    public static ForwardFragment newInstance(int selectedIndex) {
        ForwardFragment fragment = new ForwardFragment();
        Bundle args = new Bundle();
        args.putInt(SELECT_KEY, selectedIndex);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
        mViewModel = new ViewModelProvider(getActivity()).get(InboxViewModel.class);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forward_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int selectedIndex = getArguments().getInt(SELECT_KEY);

        List<Inbox> inboxList = mViewModel.getInboxList().getValue();
        Inbox selectedItem = inboxList.get(selectedIndex);

        EditText nameET = view.findViewById(R.id.dialog_name);
        EditText emailET = view.findViewById(R.id.dialog_to);
        EditText contentET = view.findViewById(R.id.dialog_content);

        nameET.setText(selectedItem.getFrom());
        emailET.setText(selectedItem.getEmail());
        contentET.setText(selectedItem.getMessage());

    }
}
