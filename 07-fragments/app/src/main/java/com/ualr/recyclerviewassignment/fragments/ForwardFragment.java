package com.ualr.recyclerviewassignment.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import androidx.lifecycle.ViewModelProvider;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.model.InboxViewModel;

import java.util.List;

public class ForwardFragment extends DialogFragment {
    private static final String TAG = ForwardFragment.class.getSimpleName();
    private static final String SELECT_KEY = "selectedIndex";
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

        final int selectedIndex = getArguments().getInt(SELECT_KEY);

        final Inbox selectedItem = mViewModel.getInboxList().getValue().get(selectedIndex);

        Button sendBtn = view.findViewById(R.id.dialog_send_btn);
        final EditText nameET = view.findViewById(R.id.dialog_name);
        final EditText emailET = view.findViewById(R.id.dialog_to);
        final EditText contentET = view.findViewById(R.id.dialog_content);

        nameET.setText(selectedItem.getFrom());
        emailET.setText(selectedItem.getEmail());
        contentET.setText(selectedItem.getMessage());

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = nameET.getText().toString();
                String newEmail = emailET.getText().toString();
                String newContent = contentET.getText().toString();

                Inbox updatedEmail = new Inbox();
                updatedEmail.setData(newName, newEmail, newContent, selectedItem.getDate(), false);

                List<Inbox> currentEmails = mViewModel.getInboxList().getValue();
                currentEmails.set(selectedIndex, updatedEmail);
                mViewModel.setInboxList(currentEmails);
                dismissDialog();
            }
        });

    }
    public void dismissDialog() {
        this.dismiss();
    }
}
