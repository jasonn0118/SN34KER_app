package com.example.sn34ker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sn34ker.datamodels.ProductModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductAddActivity extends AppCompatActivity {

    ImageView ivProduct;
    EditText etProductName,  etProductCAPrice, etProductSize;
    Spinner spProductType,spProductBrand;
    Button btnSaveProduct;
    String currentDate, selectedType,selectedBrand, selectedPicName;

    private static final int PICK_IMAGE_REQUEST = 80;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        ivProduct = findViewById(R.id.imgProduct);
        etProductName = findViewById(R.id.etProductName);
        spProductBrand = findViewById(R.id.etProductBrand);
        etProductCAPrice = findViewById(R.id.etProcuctPrice);
        etProductSize = findViewById(R.id.etProductUsSize);
        spProductType = findViewById(R.id.spProductType);
        btnSaveProduct = findViewById(R.id.btnSaveProduct);

        //Get current date
        final Date curDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        currentDate = df.format(curDate); //current formatted date.
        List<String> sneakerBrands=new ArrayList<>();
        sneakerBrands.add(0,"Select Sneaker Brand");
        sneakerBrands.add("Adidas");
        sneakerBrands.add("Nike");
        sneakerBrands.add("Under Armour");
        sneakerBrands.add("Jordan");
        sneakerBrands.add("Puma");
        sneakerBrands.add("New Balance");
        ArrayAdapter<String> brandAdapter=new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,sneakerBrands);
        spProductBrand.setAdapter(brandAdapter);
        spProductBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Toast.makeText(ProductAddActivity.this, "Please Select one of sneaker Brand.", Toast.LENGTH_SHORT).show();
                } else {
                    selectedBrand = parent.getItemAtPosition(position).toString();
                    Toast.makeText(ProductAddActivity.this, selectedBrand + " Selected.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<String> sneakerType = new ArrayList<>();
        sneakerType.add(0,"Select Sneaker Type");
        sneakerType.add("PlimSoll");
        sneakerType.add("Athletic Kick");
        sneakerType.add("High-Top");
        sneakerType.add("Authentic");
        sneakerType.add("Slip-On");
        sneakerType.add("Leather");
        sneakerType.add("Canvas");
        sneakerType.add("Extras");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, sneakerType);
        spProductType.setAdapter(typeAdapter);
        spProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Toast.makeText(ProductAddActivity.this, "Please Select one of sneaker type.", Toast.LENGTH_SHORT).show();
                } else {
                    selectedType = parent.getItemAtPosition(position).toString();
                    Toast.makeText(ProductAddActivity.this, selectedType + " Selected.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductModel myProductModel;
                try{
                    myProductModel = new ProductModel(-1, imageToStore, etProductName.getText().toString().trim(), selectedBrand, selectedType,
                            Double.parseDouble(etProductCAPrice.getText().toString().trim()), Double.parseDouble(etProductSize.getText().toString().trim()), currentDate);
                    Toast.makeText(ProductAddActivity.this, myProductModel.toString(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                } catch (Exception ex){
                    Toast.makeText(ProductAddActivity.this, "Something went wrong. "+ex.getMessage(), Toast.LENGTH_SHORT).show();
                    myProductModel = new ProductModel(-1, imageToStore,"error", "error", "error", 0, 0, "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(ProductAddActivity.this);
                boolean success = dataBaseHelper.addOne(myProductModel);

                Toast.makeText(ProductAddActivity.this, "Is Success? "+success, Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void chooseImage (View objectView){

        try{
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);
        }catch (Exception ex){
            Toast.makeText(this, "Choose Image is something wrong" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode ==RESULT_OK && data != null && data.getData() != null){
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);

                ivProduct.setImageBitmap(imageToStore);
                ivProduct.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex){
            Toast.makeText(this, "onActivityResult: " +ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
