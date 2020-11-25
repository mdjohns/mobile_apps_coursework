package com.ualr.recyclerviewassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ualr.recyclerviewassignment.fragments.ForwardFragment;
import com.ualr.recyclerviewassignment.fragments.InboxListFragment;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.model.InboxViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String FORWARD_TAG = ForwardFragment.class.getSimpleName();
    private static final String FORWARD_KEY = "FORWARD_EMAIL";

    private InboxListFragment inboxFragment;
    private InboxViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inboxFragment = (InboxListFragment) getSupportFragmentManager().findFragmentById(R.id.inbox_list_fragment);

        FloatingActionButton mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFABClicked();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_action:
                onDeleteClicked();
                return true;
            case R.id.forward_action:
                onForwardClicked();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void showSnack(String msg) {
        CoordinatorLayout parentView = findViewById(R.id.root_layout);
        int duration = Snackbar.LENGTH_LONG;
        Snackbar sb = Snackbar.make(parentView, msg, duration);

        sb.show();
    }

    public void onFABClicked() {
        if (inboxFragment != null && inboxFragment.isInLayout()) {
            inboxFragment.addEmail();
        }
    }

    public void onDeleteClicked() {
        if (inboxFragment != null && inboxFragment.isInLayout()) {
            boolean emailDeleted = inboxFragment.deleteEmail();
            if (emailDeleted) {
                String deleteMsg = getResources().getString(R.string.delete_snackbar);
                showSnack(deleteMsg);
            }
        }
    }

    public void onForwardClicked() {
        if (inboxFragment != null && inboxFragment.isInLayout()) {
//            Bundle bundle = new Bundle();
//            bundle.putInt(FORWARD_KEY, inboxFragment.getSelectedEmailPosition());
//            ForwardFragment dialog = new ForwardFragment();
//            dialog.setArguments(bundle);
//            dialog.show(getSupportFragmentManager(), FORWARD_TAG);

            inboxFragment.forwardEmail();
        }
    }

}