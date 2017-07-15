package com.mirza.frainz.frainz_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by AHMED on 08-07-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "frainz";

    private static final String TABLE_NAME = "user";

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + EMAIL + " VARCHAR(255) PRIMARY KEY," + PASSWORD + "VARCHAR(255))";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void storeInfo(String email , String password) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(PASSWORD, password);

        database.insert(TABLE_NAME, null, values);
        database.close();

    }

    public String[] getInfo() {
        String[] data=new String[2];
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            String[] projection = {
                    EMAIL,
                    PASSWORD
            };

//            String selection = EMAIL + " = ?";
//            String[] selectionArgs = {String.valueOf(level)};


            Cursor cursor = db.query(
                    TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            cursor.moveToNext();
             data[0]= cursor.getString(cursor.getColumnIndexOrThrow(EMAIL));
             data[1]= cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD));
            cursor.close();
        } catch (Exception e) {
        }

        return data;
    }

    public void removeData(){

    }


}
