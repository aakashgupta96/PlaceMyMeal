package com.example.devesh.place_my_meal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.devesh.place_my_meal.Models.OffDatabase;

/**
 * Created by devesh on 10/2/17.
 */

public class OrderDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MY_ORDER";
    public static final int DATABASE_VERSION = 1;
    public static OrderDatabase myDatabase = null ;

    public OrderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(OffDatabase.TABLE_CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        if(myDatabase == null){
            myDatabase = new OrderDatabase(context);
        }
        return myDatabase.getWritableDatabase();
    }


    public static SQLiteDatabase getReadableDatabase(Context context) {
        if(myDatabase == null){
            myDatabase = new OrderDatabase(context);
        }
        return myDatabase.getReadableDatabase();
    }

}
