/**
 * Created by Sai sreenivas on 10/13/2016.
 */
package com.example.saisreenivas.qrscanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;
        // Database Name
        private static final String DATABASE_NAME = "qrscanner";
        // Contacts table name
        private static final String TABLE_HISTORY = "qrhistory";
        private static final String TABLE_FEEDBACK = "qrfeedback1";
        // Shops Table Columns names
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_CODEFORMAT = "code_fomat";
        private static final String COLUMN_DATA = "data_scanned";
        private static final String COLUMN_DATE = "code_date";
        private static final String COLUMN_FIRSTNAME = "code_first";
        private static final String COLUMN_LASTNAME = "code_last";
        private static final String COLUMN_STARS = "code_stars";
        private static final String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DATE + " DATE,"
                + COLUMN_FIRSTNAME + " TEXT," + COLUMN_LASTNAME + " TEXT," + COLUMN_STARS + " TEXT " + ")";
        private static final String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_CODEFORMAT + " TEXT,"
                + COLUMN_DATA + " TEXT " + ")";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_HISTORY_TABLE);
        db.execSQL(CREATE_FEEDBACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
// Creating tables again
        onCreate(db);

    }

    // Adding new row in history
    public void addData(QRData dataold) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODEFORMAT, dataold.get_codeformat()); // Shop Name
        values.put(COLUMN_DATA, dataold.get_data()); // Shop Phone Number
        SQLiteDatabase db = this.getWritableDatabase();
// Inserting Row
        db.insert(TABLE_HISTORY, null, values);
        db.close(); // Closing database connection
    }


    // Deleting a row in history
    public void deleteData(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_HISTORY + " WHERE " + COLUMN_ID + " =\"" + id + "\";");
        db.close();
    }

    // Printout the database as a array of the history
    public List<QRData> databaseToArray() {
        List<QRData> totalHistory = new ArrayList<QRData>();
        SQLiteDatabase db = this.getWritableDatabase();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_HISTORY;

        //Cursor points to a location in your results
        Cursor cursor = db.rawQuery(selectQuery, null);
        //move to the first row in your results

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QRData row = new QRData();
                row.set_id(Integer.parseInt(cursor.getString(0)));
                row.set_codeformat(cursor.getString(1));
                row.set_data(cursor.getString(2));
                // Adding contact to list
                totalHistory.add(row);
            } while (cursor.moveToNext());
        }

        // return contact list
        return totalHistory;
    }


    // Printout one row of the history
    public QRData databaseRowToPrint(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY, new String[]{COLUMN_ID,
                        COLUMN_CODEFORMAT, COLUMN_DATA}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        db.close();
        if (cursor != null) {
            cursor.moveToFirst();
        }

        QRData onerow = new QRData(cursor.getString(1), cursor.getString(2));
// return shop
        return onerow;
    }

    // Getting list of the history Count
    public int getHistoryCount() {
        String countQuery = "SELECT * FROM " + TABLE_HISTORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();

        // return count
        return cnt;
    }

    // Updating the history bu one row
    public int updateHistory(QRData history) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CODEFORMAT, history.get_codeformat());
        values.put(COLUMN_DATA, history.get_data());

        // updating row
        return db.update(TABLE_HISTORY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(history.get_id())});
    }


    public void addFeed(Feed Feedback) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, Feedback.get_date());
        values.put(COLUMN_FIRSTNAME, Feedback.get_firstName()); // Shop Name
        values.put(COLUMN_LASTNAME, Feedback.get_lastName()); // Shop Phone Number
        values.put(COLUMN_STARS, Feedback.get_stars());
        SQLiteDatabase db = this.getWritableDatabase();
// Inserting Row
        db.insert(TABLE_FEEDBACK, null, values);
        db.close(); // Closing database connection
    }

    public List<Feed> databaseToArrayFeed() {
        List<Feed> totalHistoryFeed = new ArrayList<Feed>();
        SQLiteDatabase db = this.getWritableDatabase();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FEEDBACK;

        //Cursor points to a location in your results
        Cursor cursor = db.rawQuery(selectQuery, null);
        //move to the first row in your results

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Feed row = new Feed();
                row.set_id(Integer.parseInt(cursor.getString(0)));
                row.set_date(cursor.getString(1));
                row.set_firstName(cursor.getString(2));
                row.set_lastName(cursor.getString(3));
                row.set_stars(cursor.getString(4));
                // Adding contact to list
                totalHistoryFeed.add(row);
            } while (cursor.moveToNext());
        }

        // return contact list
        return totalHistoryFeed;
    }
}