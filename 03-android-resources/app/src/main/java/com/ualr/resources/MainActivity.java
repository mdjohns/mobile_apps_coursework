package com.ualr.resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

// TODO 3: Define the alternative resources needed to have different content and look n feel depending on the device language.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView countryDescripView = findViewById(R.id.country_description_text);
        String countryDescripString = this.getResources().getString(R.string.country_description);
        countryDescripView.setText(countryDescripString);
    }

    public void onLessonLearntClicked(View v) {
        String completeLessonText = this.getResources().getString(R.string.congrats_text);
        Toast toast = Toast.makeText(this, completeLessonText, Toast.LENGTH_LONG);
        toast.show();
    }

}
