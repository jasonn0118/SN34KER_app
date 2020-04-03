package com.example.sn34ker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.annotation.Nullable;

import com.example.sn34ker.datamodels.ProductModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;

    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_BRAND = "PRODUCT_BRAND";
    public static final String COLUMN_PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COLUMN_PRODUCT_US_SIZE = "PRODUCT_US_SIZE";
    public static final String COLUMN_PRODUCT_UPDATE_DATE = "PRODUCT_UPDATE_DATE";
    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_IMAGE = "PRODUCT_IMAGE";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    private static final String DATABASE_ALTER_IMAGE = "ALTER TABLE " + PRODUCT_TABLE + " ADD COLUMN " + COLUMN_PRODUCT_IMAGE + " BLOB";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "sn34ker.db", null, DATABASE_VERSION);
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
        //Updated database modeling
        if(oldVersion<2){
            db.execSQL(DATABASE_ALTER_IMAGE);
        }
    }

    public boolean addOne(ProductModel productModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Bitmap imageToStoreBitmap = productModel.getProduct_image();

        byteArrayOutputStream = new ByteArrayOutputStream();
        //Converting image
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();

        cv.put(COLUMN_PRODUCT_NAME, productModel.getName());
        cv.put(COLUMN_PRODUCT_IMAGE, imageInBytes);
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

    public Cursor readAllProductData(){
        String queryString = "SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(queryString, null);
        }
        return cursor;
    }

    public ArrayList<ProductModel> getAllProductData(){
        try{
            String queryString = "SELECT * FROM " + PRODUCT_TABLE;
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<ProductModel> productModelArrayList = new ArrayList<>();

            Cursor objectCursor = db.rawQuery(queryString, null);
            if(objectCursor.getCount() != 0){
                while(objectCursor.moveToNext()){
                    int productId = objectCursor.getInt(0);
                    String productName = objectCursor.getString(1);
                    String productBrand = objectCursor.getString(2);
                    String productType = objectCursor.getString(3);
                    Double productPrice = objectCursor.getDouble(4);
                    Double productSize = objectCursor.getDouble(5);
                    String productUpdateDate = objectCursor.getString(6);
                    byte[] productImage = objectCursor.getBlob(7);

                    Bitmap objectBitMap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);

                    productModelArrayList.add(new ProductModel(productId, objectBitMap, productName, productBrand, productType, productPrice, productSize, productUpdateDate));
                }

                return  productModelArrayList;
            } else{
                Log.d("DATABASE HELPER", "Did not call the getAllData method.");
                return null;
            }

        } catch (Exception ex) {
            Log.d("DATABASE HELPER", ex.getMessage());
            return null;
        }
    }
    public ArrayList<ProductModel> getAllNikeData(){
        try{
            SQLiteDatabase db1=getReadableDatabase();
            String[] queryString = {"PRODUCT_ID","PRODUCT_NAME","PRODUCT_BRAND","PRODUCT_TYPE","PRODUCT_PRICE","PRODUCT_US_SIZE","PRODUCT_UPDATE_DATE","PRODUCT_IMAGE"};
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<ProductModel> productModelArrayList = new ArrayList<>();

            Cursor objectCursor = db.query("PRODUCT_TABLE",queryString,"PRODUCT_BRAND =?",new String[]{"Nike"},null,null,null);
            if(objectCursor.getCount() != 0){
                while(objectCursor.moveToNext()){
                    int productId = objectCursor.getInt(0);
                    String productName = objectCursor.getString(1);
                    String productBrand = objectCursor.getString(2);
                    String productType = objectCursor.getString(3);
                    Double productPrice = objectCursor.getDouble(4);
                    Double productSize = objectCursor.getDouble(5);
                    String productUpdateDate = objectCursor.getString(6);
                    byte[] productImage = objectCursor.getBlob(7);

                    Bitmap objectBitMap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);

                    productModelArrayList.add(new ProductModel(productId, objectBitMap, productName, productBrand, productType, productPrice, productSize, productUpdateDate));
                }

                return  productModelArrayList;
            } else{
                Log.d("DATABASE HELPER", "Did not call the getAllData method.");
                return null;
            }

        } catch (Exception ex) {
            Log.d("DATABASE HELPER", ex.getMessage());
            return null;
        }
    }
    public ArrayList<ProductModel> getAllAdidasData(){
        try{
            SQLiteDatabase db1=getReadableDatabase();
            String[] queryString = {"PRODUCT_ID","PRODUCT_NAME","PRODUCT_BRAND","PRODUCT_TYPE","PRODUCT_PRICE","PRODUCT_US_SIZE","PRODUCT_UPDATE_DATE","PRODUCT_IMAGE"};
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<ProductModel> productModelArrayList = new ArrayList<>();

            Cursor objectCursor = db.query("PRODUCT_TABLE",queryString,"PRODUCT_BRAND =?",new String[]{"adidas"},null,null,null);
            if(objectCursor.getCount() != 0){
                while(objectCursor.moveToNext()){
                    int productId = objectCursor.getInt(0);
                    String productName = objectCursor.getString(1);
                    String productBrand = objectCursor.getString(2);
                    String productType = objectCursor.getString(3);
                    Double productPrice = objectCursor.getDouble(4);
                    Double productSize = objectCursor.getDouble(5);
                    String productUpdateDate = objectCursor.getString(6);
                    byte[] productImage = objectCursor.getBlob(7);

                    Bitmap objectBitMap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);

                    productModelArrayList.add(new ProductModel(productId, objectBitMap, productName, productBrand, productType, productPrice, productSize, productUpdateDate));
                }

                return  productModelArrayList;
            } else{
                Log.d("DATABASE HELPER", "Did not call the getAllData method.");
                return null;
            }

        } catch (Exception ex) {
            Log.d("DATABASE HELPER", ex.getMessage());
            return null;
        }
    }



    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+PRODUCT_TABLE);
    }
}
