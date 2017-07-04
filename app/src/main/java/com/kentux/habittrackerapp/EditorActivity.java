package com.kentux.habittrackerapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.kentux.habittrackerapp.data.HabitContract.HabitEntry;
import com.kentux.habittrackerapp.data.HabitDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mActivityEditText;

    private EditText mDescEditText;

    private Spinner mFrequencySpinner;

    private Spinner mStateSpinner;

    private EditText mTimeEditText;

    private int mFrequency = HabitEntry.FREQUENCY_UNKNOWN;

    private int mState = HabitEntry.STATE_UNKNOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mActivityEditText = (EditText) findViewById(R.id.edit_activity_name);
        mDescEditText = (EditText) findViewById(R.id.edit_habit_desc);
        mFrequencySpinner = (Spinner) findViewById(R.id.spinner_frequency);
        mStateSpinner = (Spinner) findViewById(R.id.spinner_state);
        mTimeEditText = (EditText) findViewById(R.id.edit_activity_time);
        setupFrequencySpinner();

        setupStateSpinner();
    }

    private void setupFrequencySpinner() {
        ArrayAdapter frequencySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_frequency_options,android.R.layout.simple_spinner_item);

        frequencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mFrequencySpinner.setAdapter(frequencySpinnerAdapter);

        mFrequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Hourly")) {
                        mFrequency = HabitEntry.FREQUENCY_HOURLY;
                    } else if (selection.equals("Daily")) {
                        mFrequency = HabitEntry.FREQUENCY_DAILY;
                    } else if (selection.equals("Weekly")) {
                        mFrequency = HabitEntry.FREQUENCY_WEEKLY;
                    } else if (selection.equals("Monthly")) {
                        mFrequency = HabitEntry.FREQUENCY_MONTHLY;
                    } else if (selection.equals("Yearly")) {
                        mFrequency = HabitEntry.FREQUENCY_YEARLY;
                    } else {
                        mFrequency = HabitEntry.FREQUENCY_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupStateSpinner() {
        ArrayAdapter stateSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_state_options,android.R.layout.simple_spinner_item);

        stateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mStateSpinner.setAdapter(stateSpinnerAdapter);

        mStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("TO DO")) {
                        mState = HabitEntry.STATE_TO_DO;
                    } else if (selection.equals("Completed")) {
                        mState = HabitEntry.STATE_COMPLETED;
                    } else {
                        mState = HabitEntry.STATE_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void insertHabit() {
        String activityString = mActivityEditText.getText().toString().trim();
        String descString = mDescEditText.getText().toString().trim();
        String timeString = mTimeEditText.getText().toString().trim();
        int time = Integer.parseInt(timeString);

        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT_ACTIVITY, activityString);
        values.put(HabitEntry.COLUMN_HABIT_DESC, descString);
        values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, mFrequency);
        values.put(HabitEntry.COLUMN_HABIT_STATE, mState);
        values.put(HabitEntry.COLUMN_HABIT_TIME, time);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit saved with row id: ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertHabit();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
