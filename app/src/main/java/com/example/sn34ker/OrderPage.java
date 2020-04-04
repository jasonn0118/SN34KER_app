package com.example.sn34ker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sn34ker.datamodels.OrderTableModel;
import com.example.sn34ker.datamodels.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OrderPage extends AppCompatActivity {

    ImageView orderImage;
    TextView orderName;
    TextView orderBrand;
    TextView orderSize;
    TextView orderPrice;
    EditText cusName;
    EditText cusAdd;
    EditText cusPOS;
    EditText cardNumber,pin,expDate;
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
        cardNumber=findViewById(R.id.cusCard);
        pin=findViewById(R.id.cusPin);
        expDate=findViewById(R.id.cusExpDate);

        orderName.setText(getIntent().getStringExtra("NAME"));
        orderBrand.setText(getIntent().getStringExtra("BRAND"));
        orderSize.setText(getIntent().getStringExtra("SIZE"));
        orderPrice.setText(getIntent().getStringExtra("PRICE"));



        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        String id = currentUser.getUid();
                        String currentUserEmail = currentUser.getEmail();
                String name = cusName.getText().toString();
                String productName=orderName.getText().toString();
                String productBrand=orderBrand.getText().toString();
                Double productSize=Double.parseDouble(orderSize.getText().toString());
                Double productPrice=Double.parseDouble(orderPrice.getText().toString());
                String address = cusAdd.getText().toString();
                String pos = cusPOS.getText().toString();
                String cardNum= cardNumber.getText().toString();
                int pinCode=Integer.parseInt(pin.getText().toString());
                int expDat=Integer.parseInt(expDate.getText().toString());
                int produxtId=Integer.parseInt(getIntent().getStringExtra("PRODUCTID"));
                Toast.makeText(OrderPage.this, ""+produxtId, Toast.LENGTH_SHORT).show();
                OrderTableModel order1;

                try {
                    order1=new OrderTableModel(-1,produxtId,pinCode,expDat,name,address,pos,cardNum,id,productName,productBrand,productPrice,productSize);
                    Toast.makeText(OrderPage.this, order1.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OrderPage.this, ConfirmationPage.class);
                    intent.putExtra("NAME", name);
                    intent.putExtra("ADDRESS", address);
                    intent.putExtra("POS", pos);

                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(OrderPage.this, "Sometthing went Wrong", Toast.LENGTH_SHORT).show();
                    order1=new OrderTableModel(-1,0,0,0,"error","error","error",
                            "error","error","error","error",0,0);


                }
                DataBaseHelper dataBaseHelper=new DataBaseHelper(OrderPage.this);
                boolean success = dataBaseHelper.addOrderOne(order1);
                Toast.makeText(OrderPage.this, "the outcome is ? "+success, Toast.LENGTH_SHORT).show();

                
            }
        });
    }
}
