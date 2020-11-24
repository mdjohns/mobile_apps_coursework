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

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

public class ForwardFragment extends DialogFragment {
    private static final String TAG = ForwardFragment.class.getSimpleName();
    private static final String FORWARD_KEY = "FORWARD_EMAIL";
    private Inbox selectedItem;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);


    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            selectedItem = getArguments().getParcelable(FORWARD_KEY);
        }
        return inflater.inflate(R.layout.forward_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nameET = view.findViewById(R.id.dialog_name);
        EditText emailET = view.findViewById(R.id.dialog_to);
        EditText contentET = view.findViewById(R.id.dialog_content);

        nameET.setText(selectedItem.getFrom());
        emailET.setText(selectedItem.getEmail());
        contentET.setText(selectedItem.getMessage());
    }
}
