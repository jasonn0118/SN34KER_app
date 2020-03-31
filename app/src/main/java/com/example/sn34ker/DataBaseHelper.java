package com.example.sn34ker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_BRAND = "PRODUCT_BRAND";
    public static final String COLUMN_PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COLUMN_PRODUCT_US_SIZE = "PRODUCT_US_SIZE";
    public static final String COLUMN_PRODUCT_UPDATE_DATE = "PRODUCT_UPDATE_DATE";
    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "sn34ker.db", null, 1);
    }

    // This is called the first time a db is accessed. There should be code in here to create a new db.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PRODUCT_TABLE + " (" + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_PRODUCT_BRAND + " TEXT, " + COLUMN_PRODUCT_TYPE + " TEXT, " + COLUMN_PRODUCT_PRICE + " DECIMAL, " + COLUMN_PRODUCT_US_SIZE + " DECIMAL, " + COLUMN_PRODUCT_UPDATE_DATE + " DATE)";

        db.execSQL(createTableStatement);
    }

    // This is called if the db version number changes. It prevents previous users apps from breaking when you change the db design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ProductModel productModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT_NAME, productModel.getName());
        cv.put(COLUMN_PRODUCT_BRAND, productModel.getBrand());
        cv.put(COLUMN_PRODUCT_TYPE, productModel.getType());
        cv.put(COLUMN_PRODUCT_PRICE, productModel.getCA_price());
        cv.put(COLUMN_PRODUCT_US_SIZE, productModel.getUS_Size());
        cv.put(COLUMN_PRODUCT_UPDATE_DATE, productModel.getUpdatedDate());

        long insert = db.insert(PRODUCT_TABLE,null,cv);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
