package com.example.sn34ker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edEmail, edPass, edRepass;
    Button btnRegi;
    TextView txtLogin;
    FirebaseAuth fAuth;
    ProgressBar pbRegi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edEmail = findViewById(R.id.edEmailRegiInput);
        edPass = findViewById(R.id.edPassRegiInput);
        edRepass = findViewById(R.id.edPassConfirm);
        btnRegi = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLoginInfo);

        fAuth = FirebaseAuth.getInstance();
        pbRegi = findViewById(R.id.pbRegi);

        //If user is logging in.
        if(fAuth.getCurrentUser() != null){
          startActivity(new Intent(getApplicationContext(), MainActivity.class));
          finish();
         }

        btnRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString().trim();
                String password = edPass.getText().toString().trim();
                String rePass = edRepass.getText().toString().trim();


                //Input Validation.
                if(TextUtils.isEmpty(email)){
                    edEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    edPass.setError("Password is required.");
                    return;
                }
                if(TextUtils.isEmpty(rePass)){
                    edRepass.setError("Confirmed password is required.");
                    return;
                }
                if(password.length() < 8){
                    edPass.setError("Password is longer than 8 characters");
                    return;
                }
//                //Password match.
//                if(password != rePass){
//                    edRepass.setError("Password must be match.");
//                    return;
//                }

                pbRegi.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try{
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else{
                                Toast.makeText(RegisterActivity.this, "Sorry, we couldn't make a user for some reasons.", Toast.LENGTH_SHORT).show();
                                pbRegi.setVisibility(View.GONE);
                            }
                        } catch(Exception ex){
                            Toast.makeText(RegisterActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
