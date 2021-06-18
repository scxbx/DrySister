package com.example.drysister.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.drysister.DrySisterApp;

public class SisterDBHelper {

    private static final String TAG = "SisterDBHelper";

    private static SisterDBHelper dbHelper;
    private SisterOpenHelper sqlHelper;
    private SQLiteDatabase db;

    private SisterDBHelper() {
        sqlHelper = new SisterOpenHelper(DrySisterApp.getContext());
    }
}
