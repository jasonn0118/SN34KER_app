package com.example.sn34ker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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

    //Get every product list.
    public List<ProductModel> getEveryThing(){
        List<ProductModel> returnList = new ArrayList<>();

        //Get Product data from our data base.
        String queryString = "SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            // loop through the result
            do{
                int productId = cursor.getInt(0);
                String productName = cursor.getString(1);
                String productBrand = cursor.getString(2);
                String productType = cursor.getString(3);
                Double productPrice = cursor.getDouble(4);
                Double productSize = cursor.getDouble(5);
                String productUpdateDate = cursor.getString(6);

                ProductModel myProduct = new ProductModel(productId, productName, productBrand, productType, productPrice, productSize, productUpdateDate);
                returnList.add(myProduct);
            } while(cursor.moveToNext());
        } else {
            //failure. do not add anything at this point.
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public Cursor readAllProductData(){
        String queryString = "SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(queryString, null);
        }
        return cursor;
    }
}
