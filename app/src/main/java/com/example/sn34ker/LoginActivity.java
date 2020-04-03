package com.example.sn34ker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPass;
    Button btnLogin;
    TextView txtRegi;
    ProgressBar pbLogin;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmailInput);
        edPass = findViewById(R.id.edPassInput);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegi = findViewById(R.id.txtRegister);
        pbLogin = findViewById(R.id.pbLogin);
        fAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString().trim();
                String password = edPass.getText().toString().trim();

                //Input Validation
                if(TextUtils.isEmpty(email)){
                    edEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    edPass.setError("Password is required");
                    return;
                }
                if(password.length()<8){
                    edPass.setError("Password must be more than 8 characters.");
                    return;
                }

                //Showing progress bar
                pbLogin.setVisibility(View.VISIBLE);

                //Authentication User
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try{
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Welcome to Sn34ker!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "The username or password you entered is invalid", Toast.LENGTH_SHORT).show();
                                pbLogin.setVisibility(View.GONE);
                            }
                        } catch (Exception ex){
                            Toast.makeText(LoginActivity.this, "Sorry, Something goes wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        txtRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}
