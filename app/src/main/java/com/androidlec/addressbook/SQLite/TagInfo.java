package com.androidlec.addressbook.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TagInfo extends SQLiteOpenHelper {

    public TagInfo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE tag( " +
                "tSeqno INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tName TEXT " +
                ");";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS tag; ";

        sqLiteDatabase.execSQL(query);

        onCreate(sqLiteDatabase);
    }
}