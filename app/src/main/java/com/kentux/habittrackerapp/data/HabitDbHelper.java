package com.kentux.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.kentux.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Created by Tiago Gomes on 03/07/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "habitTracker.db";

    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "Create TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_ACTIVITY + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DESC + " TEXT, "
                + HabitEntry.COLUMN_HABIT_FREQUENCY + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_STATE + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_TIME + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
