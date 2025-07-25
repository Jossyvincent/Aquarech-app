package com.aquarech.farmer.db.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.aquarech.farmer.db.DbHelper;
import com.aquarech.farmer.utils.Config;


public class UserProvider extends ContentProvider {


    public static final String USER_PROVIDER_NAME = "com.aquarech.farmer.db.providers.UserProvider";
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + USER_PROVIDER_NAME + "/useritems");

    public static final String[] USER_PROJECTION = {
            Config.COLUMN_USER_ID,
            Config.COLUMN_PHONE,
            Config.COLUMN_PASSWORD

    };

    private static final int USER_ITEMS = 1;
    private static final int USER_ITEM_ID = 2;
    private static final UriMatcher uriMatcher = getUriMatcher();

    DbHelper database = null;

    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(USER_PROVIDER_NAME, "useritems", USER_ITEMS);
        uriMatcher.addURI(USER_PROVIDER_NAME, "useritems/#", USER_ITEM_ID);
        return uriMatcher;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER_ITEMS:
                return "vnd.android.cursor.dir/vnd.com.aquarech.farmer.androidcontentprovider.provider.useritems";
            case USER_ITEM_ID:
                return "vnd.android.cursor.item/vnd.com.aquarech.farmer.androidcontentprovider.provider.useritems";
        }
        return "";
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        database = DbHelper.getInstance(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;

        if (uriMatcher.match(uri) == USER_ITEM_ID) {
            id = uri.getPathSegments().get(1);
        }

        return database.getDataInTable(id, projection, selection, selectionArgs, sortOrder, Config.TABLE_USERS, Config.COLUMN_USER_ID);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            long id = database.insertDataToTable(values, Config.TABLE_USERS);
            Uri returnUri = ContentUris.withAppendedId(USER_CONTENT_URI, id);
            return returnUri;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String id = null;

        if (uriMatcher.match(uri) == USER_ITEM_ID) {
            id = uri.getPathSegments().get(1);
        }

        return database.deleteDataInTable(id, Config.TABLE_USERS);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String id = null;
        if (uriMatcher.match(uri) == USER_ITEM_ID) {
            id = uri.getPathSegments().get(1);
        }

        return database.updateDataInTable(id, values, Config.TABLE_USERS);
    }
}
