package com.example.sn34ker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductAddActivity extends AppCompatActivity {

    EditText etProductName, etProductBrand, etProductCAPrice, etProductSize;
    Spinner spProductType;
    Button btnSaveProduct;
    String currentDate, selectedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        etProductName = findViewById(R.id.etProductName);
        etProductBrand = findViewById(R.id.etProductBrand);
        etProductCAPrice = findViewById(R.id.etProcuctPrice);
        etProductSize = findViewById(R.id.etProductUsSize);
        spProductType = findViewById(R.id.spProductType);
        btnSaveProduct = findViewById(R.id.btnSaveProduct);
        //Get current date
        final Date curDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        currentDate = df.format(curDate); //current formatted date.

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
                this, R.layout.support_simple_spinner_dropdown_item, sneakerType
        );
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
                    myProductModel = new ProductModel(-1, etProductName.getText().toString().trim(), etProductBrand.getText().toString().trim(), selectedType,
                            Double.parseDouble(etProductCAPrice.getText().toString().trim()), Double.parseDouble(etProductSize.getText().toString().trim()), currentDate);
//                    Toast.makeText(ProductAddActivity.this, myProductModel.toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                } catch (Exception ex){
                    Toast.makeText(ProductAddActivity.this, "Something went wrong. "+ex.getMessage(), Toast.LENGTH_SHORT).show();
                    myProductModel = new ProductModel(-1,"error", "error", "error", 0, 0, "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(ProductAddActivity.this);
                boolean success = dataBaseHelper.addOne(myProductModel);

                Toast.makeText(ProductAddActivity.this, "Is Success? "+success, Toast.LENGTH_SHORT).show();
            }
        });








    }
}
