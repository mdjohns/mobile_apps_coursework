package com.ualr.simpletasklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ualr.simpletasklist.databinding.ActivityMainBinding;
import com.ualr.simpletasklist.model.TaskList;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private TaskList tasks;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.tasks = new TaskList();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        //TODO 06.02 Bind the onAddBtnClicked method to the add button, so the onAddBtnClicked is
        // triggered whenever the user clicks on that button

        //TODO 07.02 Bind the onDeleteBtnClicked method to the delete button, so that method is
        // triggered whenever the user clicks on that button

        //TODO 08.02 Bind the onDoneBtnClicked method to the done button, so the onDoneBtnClicked method is
        // triggered whenever the user clicks on that button
    }

    public void displayErrorToast(String err) {
        Toast toast = Toast.makeText(this, err, Toast.LENGTH_SHORT);
        toggleSoftKeyboard();
        toast.show();
    }
    public void toggleSoftKeyboard() {
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    public void onAddBtnClicked(View v) {
        String task = binding.editTextTaskDescription.getText().toString();
        tasks.add(task);
        toggleSoftKeyboard();

        binding.editTextTaskDescription.setText("");
        binding.taskList.setText(tasks.toString());

    }

    public void onDeleteBtnClicked(View v) {
        try {
            Integer taskId = Integer.parseInt(binding.editTextTaskId.getText().toString());
            tasks.delete(taskId);
            toggleSoftKeyboard();
            binding.editTextTaskId.setText("");

            if (tasks.getTasks().size() == 0) {
                binding.taskList.setText(R.string.task_list_empty);
            }
            else {
                binding.taskList.setText(tasks.toString());
            }
        }
        catch (Exception e) {
            Log.d(TAG, Arrays.toString(e.getStackTrace()));
            displayErrorToast(e.getMessage());
        }
    }

    public void onDoneBtnClicked(View v) {
        try {
            Integer taskId = Integer.parseInt(binding.editTextTaskId.getText().toString());
            tasks.markDone(taskId);
            toggleSoftKeyboard();

            binding.editTextTaskId.setText("");
            binding.taskList.setText(tasks.toString());
        }
        catch (Exception e) {
            Log.d(TAG, Arrays.toString(e.getStackTrace()));
            displayErrorToast(e.getMessage());
        }
    }
}