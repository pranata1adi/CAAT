package com.example.aseps.caat.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.aseps.caat.Temp.home.ItemHome;
import com.example.aseps.caat.Temp.home.ItemPB;

import java.util.ArrayList;
import java.util.List;

import static com.example.aseps.caat.helper.DBString.*;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_CAAT, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " +
                DATABASE_CAAT +
                "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY, " +
                COLUMN_NAME +
                " TEXT, " +
                COLUMN_NILAI +
                " INTEGER" + ")";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CAAT);
        onCreate(db);
    }

    public List<ItemPB> getAll() {
        String sql = "select " + COLUMN_ID + " , " + COLUMN_NAME + " , " + " count ( " + COLUMN_NILAI +
                " ) , sum(" + COLUMN_NILAI + ") " + "from " + DATABASE_CAAT + " group by " + COLUMN_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        List<ItemPB> items = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int statusAll = cursor.getInt(2);
                int nilaiPb = cursor.getInt(3);
                Log.d("statussss", String.valueOf(statusAll));
                Log.d("statussss", String.valueOf(nilaiPb));
                items.add(new ItemPB(id, name, statusAll, nilaiPb));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return items;
    }

    public void addDB(ItemHome item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getTitle());
        values.put(COLUMN_NILAI, item.getStatus());

        Log.d("title", item.getTitle());
        Log.d("title", String.valueOf(item.getStatus()));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DATABASE_CAAT, null, values);
    }

    public void deleteItem() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table " + DATABASE_CAAT);
        String CREATE_TODO_TABLE = "CREATE TABLE " +
                DATABASE_CAAT +
                "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY, " +
                COLUMN_NAME +
                " TEXT, " +
                COLUMN_NILAI +
                " INTEGER" + ")";
        db.execSQL(CREATE_TODO_TABLE);
    }
}