package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.data.PetContract.PetEntry;

public class PetDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = PetDbHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "shelter.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ", ";
    public static final String NOT_NULL = " NOT NULL";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + " (" +
                    PetEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    PetEntry.COLUMN_PET_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    PetEntry.COLUMN_PET_BREED + TEXT_TYPE + COMMA_SEP +
                    PetEntry.COLUMN_PET_GENDER + INTEGER_TYPE + NOT_NULL + COMMA_SEP +
                    PetEntry.COLUMN_PET_WEIGHT + INTEGER_TYPE + NOT_NULL + " DEFAULT 0 );";

    private static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PetContract.PetEntry.TABLE_NAME;

    public PetDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
