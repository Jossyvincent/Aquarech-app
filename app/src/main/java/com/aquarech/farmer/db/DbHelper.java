package com.aquarech.farmer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aquarech.farmer.utils.Config;

public class DbHelper extends SQLiteOpenHelper {
    // constructor
    public DbHelper(Context context) {
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + Config.TABLE_USERS + "("
                + Config.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Config.COLUMN_PHONE + " TEXT UNIQUE,"
                + Config.COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_USERS);
        onCreate(db);
    }

}
