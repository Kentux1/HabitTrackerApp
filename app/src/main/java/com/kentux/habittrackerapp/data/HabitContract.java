package com.kentux.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by Tiago Gomes on 03/07/2017.
 */

public class HabitContract {

    private HabitContract() {}

        public static abstract class HabitEntry implements BaseColumns {

            public final static String TABLE_NAME = "habits";

            public final static String _ID = BaseColumns._ID;
            public final static String COLUMN_HABIT_ACTIVITY = "activity";
            public final static String COLUMN_HABIT_DESC = "description";
            public final static String COLUMN_HABIT_STATE = "state";
            public final static String COLUMN_HABIT_FREQUENCY = "frequency";
            public final static String COLUMN_HABIT_TIME = "time";

            public static final int STATE_UNKNOWN = 0;
            public static final int STATE_TO_DO = 1;
            public static final int STATE_COMPLETED = 2;

            public static final int FREQUENCY_UNKNOWN = 0;
            public static final int FREQUENCY_HOURLY = 1;
            public static final int FREQUENCY_DAILY = 2;
            public static final int FREQUENCY_WEEKLY = 3;
            public static final int FREQUENCY_MONTHLY = 4;
            public static final int FREQUENCY_YEARLY = 5;
        }
}
