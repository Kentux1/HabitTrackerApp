package com.kentux.habittrackerapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import com.kentux.habittrackerapp.data.HabitContract.HabitEntry;
import com.kentux.habittrackerapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private String[] projection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new HabitDbHelper(this);
    }

    private Cursor readDatabase() {
        projection = new String[] {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_ACTIVITY,
                HabitEntry.COLUMN_HABIT_DESC,
                HabitEntry.COLUMN_HABIT_FREQUENCY,
                HabitEntry.COLUMN_HABIT_STATE,
                HabitEntry.COLUMN_HABIT_TIME};

        cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }
}
