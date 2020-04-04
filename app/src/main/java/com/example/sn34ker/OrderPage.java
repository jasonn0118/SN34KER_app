package com.example.sn34ker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sn34ker.datamodels.ProductModel;

public class OrderPage extends AppCompatActivity {

    ImageView orderImage;
    TextView orderName;
    TextView orderBrand;
    TextView orderSize;
    TextView orderPrice;
    EditText cusName;
    EditText cusAdd;
    EditText cusPOS;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        orderImage = findViewById(R.id.orderImage);
        orderName = findViewById(R.id.orderName);
        orderBrand = findViewById(R.id.orderBrand);
        orderSize = findViewById(R.id.orderSize);;
        orderPrice = findViewById(R.id.orderPrice);
        cusName = findViewById(R.id.cusName);
        cusAdd = findViewById(R.id.cusAdd);
        cusPOS = findViewById(R.id.cusPOS);
        confirmButton = findViewById(R.id.confirmButton);

        orderName.setText(getIntent().getStringExtra("NAME"));
        orderBrand.setText(getIntent().getStringExtra("BRAND"));
        orderSize.setText(getIntent().getStringExtra("SIZE"));
        orderPrice.setText(getIntent().getStringExtra("PRICE"));

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cusName.getText().toString();
                String add = cusAdd.getText().toString();
                String pos = cusPOS.getText().toString();

                Intent intent = new Intent(OrderPage.this, ConfirmationPage.class);
                intent.putExtra("NAME", name);
                intent.putExtra("ADDRESS", add);
                intent.putExtra("POS", pos);

                startActivity(intent);
            }
        });
    }
}
