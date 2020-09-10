package com.ualr.helloworld;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ualr.helloworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // TODO 02. We replace members referring to GUI elements with a member of the new Binding class
    //  automatically created
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO 03. Initialize the binding reference using the inflate method of the Binding class
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        // TODO 04. Get a reference to the root view
        View rootView = mBinding.getRoot();
        // TODO 05. Make the view active to display it on the screen
        setContentView(rootView);
    }

    public void showTextMessage(View view) {
        // TODO 06. We use the binding reference to access the GUI elements and get/modify their properties
        mBinding.userMsgTV.setText(mBinding.userInputET.getText().toString());
    }
}
