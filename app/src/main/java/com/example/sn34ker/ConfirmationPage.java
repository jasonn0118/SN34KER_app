package com.example.sn34ker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationPage extends AppCompatActivity {

    TextView txtViewInfo;
    TextView txtViewInfo2;
    TextView txtViewInfo3;
    Button btnShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);

        txtViewInfo = findViewById(R.id.txtViewInfo);
        txtViewInfo2 = findViewById(R.id.txtViewInfo2);
        txtViewInfo3 = findViewById(R.id.txtViewInfo3);
        btnShop = findViewById(R.id.btnShop);

        txtViewInfo.setText("Customer: " + getIntent().getStringExtra("NAME"));
        txtViewInfo2.setText("Address: " + getIntent().getStringExtra("ADDRESS"));
        txtViewInfo3.setText("Postal code: " + getIntent().getStringExtra("POS"));

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
