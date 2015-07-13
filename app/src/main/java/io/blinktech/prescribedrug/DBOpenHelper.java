package io.blinktech.prescribedrug;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mayank on 7/12/15.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    //Constants for db name and version
    private static final String DATABASE_NAME = "wall.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_DRUG = "drug";
    public static final String DRUG_ID = "_id";
    public static final String DRUG_NAME = "name";

    //SQL to create table
    private static final String DRUG_CREATE =
            "CREATE TABLE " + TABLE_DRUG + " (" +
                    DRUG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DRUG_NAME + " TEXT NOT NULL " +
                    ")";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DRUG_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUG);
        onCreate(sqLiteDatabase);
    }
}