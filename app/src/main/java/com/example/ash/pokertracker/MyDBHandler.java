package com.example.ash.pokertracker;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="results.db";
    public static final String TABLE_RESULTS = "results";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_BUYIN = "buyin";
    public static final String COLUMN_RETURN = "return";
    public static final String COLUMN_DATE = "date";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createResultTable = "CREATE TABLE " + TABLE_RESULTS +
                "("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR, " +
                COLUMN_TYPE + " VARCHAR, " +
                COLUMN_BUYIN + " INTEGER, " +
                COLUMN_RETURN + " INTEGER, " +
                COLUMN_DATE + " DATE " +
                ");";
        db.execSQL(createResultTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP_TABLE_IF_EXISTS "+ TABLE_RESULTS);
        onCreate(db);
    }
    //Add new result to the database
    public  void addResult(ResultData result)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, result.get_name());
        values.put(COLUMN_TYPE, result.get_type());
        values.put(COLUMN_BUYIN, result.get_buyIn());
        values.put(COLUMN_RETURN, result.get_return());
        values.put(COLUMN_DATE, result.get_date());
        //Get reference to the database
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RESULTS, null, values);
        db.close();
    }

    //Delete a result
    public void deleteResult(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RESULTS + " WHERE " + COLUMN_ID + " =\"" + id + "\";");

    }

    public int calculateProfit()
    {
        int totalBuyInAmount = 0;
        int totalReturnAmount = 0;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_BUYIN + ", " + COLUMN_RETURN + " FROM " + TABLE_RESULTS + ";";
        Cursor c = db.rawQuery(query, null);
        if(c.getCount()> 0) {
            c.moveToFirst();

            while (!c.isAfterLast()) {
                System.out.println(c.getColumnIndex("buyin"));
                System.out.println(c.getColumnIndex("return"));

                totalBuyInAmount += c.getInt(c.getColumnIndex("buyin"));
                totalReturnAmount += c.getInt(c.getColumnIndex("return"));
                c.moveToNext();
            }

            db.close();
            c.close();
            return totalReturnAmount - totalBuyInAmount;
        }else{
            db.close();
            c.close();
            return 0;
        }

    }

    public String getMostRecentResultData ()
    {
        String resultString;
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_RESULTS + " WHERE " + COLUMN_ID + " = (SELECT MAX("+COLUMN_ID+") FROM "+TABLE_RESULTS+" );";
        //System.out.println(query);

        Cursor c = db.rawQuery(query, null);
        if(c.getCount()!= 0) {
            c.moveToFirst();
            //System.out.println(c.getInt(c.getColumnIndex("buyin")));
            //System.out.println(c.getInt(c.getColumnIndex("return")));
            int profit = c.getInt(c.getColumnIndex("return")) - c.getInt(c.getColumnIndex("buyin"));
            if (profit < 0) {
                resultString = "Most recent result: -£" + Math.abs(profit) + " From " + c.getString(c.getColumnIndex("name")) + " " + c.getString(c.getColumnIndex("type"));

            } else {
                resultString = "Most recent result: +£" + profit + " From " + c.getString(c.getColumnIndex("name")) + " " + c.getString(c.getColumnIndex("type"));
            }
        }else
        {
            resultString = " No Results Yet!";
        }
        db.close();
        c.close();
        return resultString;
    }


}
