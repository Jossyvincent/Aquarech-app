package com.aquarech.farmer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.aquarech.farmer.R;
import com.aquarech.farmer.utils.Config;

import java.sql.SQLException;

public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper instance = null;

    Context context;
    // constructor
    public DbHelper(Context context) {
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
        this.context = context;
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

    public static DbHelper getInstance(Context ctx){
        if(instance == null){
            instance = new DbHelper(ctx.getApplicationContext());
        }

        return instance;
    }

    public Cursor getDataInTable(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder, String tableName, String sortKey) {
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(tableName);

        if(id != null){
            sqliteQueryBuilder.appendWhere("_id" + " = " + id);
        }

        if(sortOrder != null || sortOrder != ""){
            sortOrder = sortKey;
        }

        Cursor cursor = sqliteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        return cursor;
    }

    public long insertDataToTable(ContentValues values, String tableName) throws SQLException {
        long id = getWritableDatabase().insert(tableName, "", values);

        if(id <= 0){
            throw new SQLException(context.getString(R.string.insert_error));
        }

        return id;
    }

    public int deleteDataInTable(String id, String tableName){
        if(id == null){
            return getWritableDatabase().delete(tableName, null , null);
        }
        else{
            return getWritableDatabase().delete(tableName, "_id = ?", new String[]{id});
        }
    }

    public int updateDataInTable(String id, ContentValues values, String tableName){
        if(id == null){
            return getWritableDatabase().update(tableName, values, null, null);
        }
        else{
            return getWritableDatabase().update(tableName, values, "_id = ?", new String[]{id});
        }
    }

}
