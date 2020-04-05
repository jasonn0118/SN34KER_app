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
import com.example.sn34ker.datamodels.UserModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 4;

    //Product table
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_BRAND = "PRODUCT_BRAND";
    public static final String COLUMN_PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COLUMN_PRODUCT_US_SIZE = "PRODUCT_US_SIZE";
    public static final String COLUMN_PRODUCT_UPDATE_DATE = "PRODUCT_UPDATE_DATE";
    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_IMAGE = "PRODUCT_IMAGE";

    //User table
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_ID = "COLUMN_USER_ID";
    public static final String COLUMN_USER_FIRSTNAME = "COLUMN_USER_FIRSTNAME";
    public static final String COLUMN_USER_LASTNAME = "COLUMN_USER_LASTNAME";
    public static final String COLUMN_USER_GENDER = "COLUMN_USER_GENDER";
    public static final String COLUMN_USER_EMAIL = "COLUMN_USER_EMAIL";
    public static final String COLUMN_USER_STREET_2 = "COLUMN_USER_STREET2";
    public static final String COLUMN_USER_STREET_1 = "COLUMN_USER_STREET1";
    public static final String COLUMN_USER_CITY = "COLUMN_USER_CITY";
    public static final String COLUMN_USER_PROVINCE = "COLUMN_USER_PROVINCE";
    public static final String COLUMN_USER_POSTCODE = "COLUMN_USER_POSTCODE";
    public static final String COLUMN_USER_DOB = "COLUMN_USER_DOB";
    public static final String COLUMN_USER_PROFILE = "COLUMN_USER_PROFILE";

    private ByteArrayOutputStream byteArrayOutputStream, userByteArrayOutputStream;
    private byte[] imageInBytes, userImageInBytes;

    private static final String DATABASE_ALTER_IMAGE = "ALTER TABLE " + PRODUCT_TABLE + " ADD COLUMN " + COLUMN_PRODUCT_IMAGE + " BLOB";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USER_ID + " STRING, " +
            COLUMN_USER_FIRSTNAME + " TEXT, " + COLUMN_USER_LASTNAME + " TEXT, " + COLUMN_USER_GENDER + " TEXT, " +
            COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_STREET_2 + " TEXT, " + COLUMN_USER_STREET_1 + " TEXT, " + COLUMN_USER_CITY + " TEXT, " +
            COLUMN_USER_PROVINCE + " TEXT, " + COLUMN_USER_POSTCODE + " TEXT, " + COLUMN_USER_DOB + " DATE, " + COLUMN_USER_PROFILE + " BLOB)";
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
        if(oldVersion<4){
            db.execSQL(CREATE_USER_TABLE);
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

    public boolean updateUserToDb(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Bitmap imageToStoreBitmap = userModel.getUser_profile_image();

        userByteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 20, userByteArrayOutputStream);
        userImageInBytes = userByteArrayOutputStream.toByteArray();

        cv.put(COLUMN_USER_ID, userModel.getUserId());
        cv.put(COLUMN_USER_FIRSTNAME, userModel.getFirstName());
        cv.put(COLUMN_USER_LASTNAME, userModel.getLastName());
        cv.put(COLUMN_USER_GENDER, userModel.getGender());
        cv.put(COLUMN_USER_EMAIL, userModel.getEmail());
        cv.put(COLUMN_USER_STREET_2, userModel.getStreet2());
        cv.put(COLUMN_USER_STREET_1, userModel.getStreet());
        cv.put(COLUMN_USER_CITY, userModel.getCity());
        cv.put(COLUMN_USER_PROVINCE, userModel.getProvince());
        cv.put(COLUMN_USER_POSTCODE, userModel.getPostalCode());
        cv.put(COLUMN_USER_DOB, userModel.getDateOfBirth());
        cv.put(COLUMN_USER_PROFILE, userImageInBytes);

        long insertIndb = db.insert(USER_TABLE, null, cv);

        if(insertIndb == -1){
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
    public ArrayList<ProductModel> getAllNikeData(String choice){
        try{
            SQLiteDatabase db1=getReadableDatabase();
            String[] queryString = {"PRODUCT_ID","PRODUCT_NAME","PRODUCT_BRAND","PRODUCT_TYPE","PRODUCT_PRICE","PRODUCT_US_SIZE","PRODUCT_UPDATE_DATE","PRODUCT_IMAGE"};
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<ProductModel> productModelArrayList = new ArrayList<>();

            Cursor objectCursor = db.query("PRODUCT_TABLE",queryString,"PRODUCT_BRAND =?",new String[]{choice},null,null,null);
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
        db.execSQL("DELETE FROM "+USER_TABLE);
    }


    public void deleteProductAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+PRODUCT_TABLE);
    }
}
