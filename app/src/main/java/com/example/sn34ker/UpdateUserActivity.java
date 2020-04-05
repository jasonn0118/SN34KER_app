package com.example.sn34ker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sn34ker.datamodels.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateUserActivity extends AppCompatActivity {

    CircleImageView ivUserProfile;
    EditText etLastName, etFirstName, etDob, etStreet1, etStreet2, etCity, etPostalCode;
    Spinner spGender, spProvince;
    TextView txtEmail;
    String selectedGen,  selectedProv;
    Button btnUserUpdate;
    Boolean flag = false;

    private static final int PICK_IMAGE_REQUEST = 20;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        ivUserProfile = (CircleImageView) findViewById(R.id.img_userProfile);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etDob = findViewById(R.id.etDoB);
        etStreet1 = findViewById(R.id.etStreet1);
        etStreet2 = findViewById(R.id.etStreet2);
        etCity = findViewById(R.id.etCity);
        etPostalCode = findViewById(R.id.etPostalCode);
        spGender = findViewById(R.id.spGender);
        spProvince = findViewById(R.id.spProvince);
        txtEmail = findViewById(R.id.txtUserEmailView);
        btnUserUpdate = findViewById(R.id.btnUpdateCurUser);

        //Current user called.
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String CurUserEmail = currentUser.getEmail();
        txtEmail.setText(CurUserEmail);


        List<String> gender = new ArrayList<>();
        gender.add(0,"Select gender");
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, gender
        );
        spGender.setAdapter(genderAdapter);
        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                } else{
                    selectedGen = parent.getItemAtPosition(position).toString();
                    Toast.makeText(UpdateUserActivity.this, selectedGen + " Selected.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> provinceCode = new ArrayList<>();
        provinceCode.add("NL");
        provinceCode.add("PE");
        provinceCode.add("NS");
        provinceCode.add("NB");
        provinceCode.add("QC");
        provinceCode.add("ON");
        provinceCode.add("MB");
        provinceCode.add("SK");
        provinceCode.add("AB");
        provinceCode.add("BC");
        provinceCode.add("YT");
        provinceCode.add("NT");
        provinceCode.add("NU");
        Collections.sort(provinceCode);
        provinceCode.add(0,"Select Province");

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, provinceCode);
        spProvince.setAdapter(provinceAdapter);
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                } else{
                    selectedProv =  parent.getItemAtPosition(position).toString();
                    Toast.makeText(UpdateUserActivity.this, selectedProv + " selected.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnUserUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFirstName.getText().toString().equals("") || etLastName.getText().toString().equals("") || spGender.getSelectedItemPosition() == 0 || etDob.getText().toString().equals("")
                    || etStreet1.getText().toString().equals("") || etStreet2.getText().toString().equals("") || etCity.getText().toString().equals("") || spProvince.getSelectedItemPosition() == 0
                    || etPostalCode.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields must be entered", Toast.LENGTH_SHORT).show();
                } else if (etDob.getText().toString().length() != 8) {
                    Toast.makeText(getApplicationContext(), "The format is: DD-MM-YYYY ", Toast.LENGTH_SHORT);
                } else if (flag == false){
                    Toast.makeText(getApplicationContext(), "Please choose an image", Toast.LENGTH_SHORT).show();
                }
                else {
                    UserModel myUserModel;
                    try {
                        myUserModel = new UserModel(currentUser.getUid(), etFirstName.getText().toString().trim(), etLastName.getText().toString().trim(),
                                selectedGen, currentUser.getEmail(), etStreet2.getText().toString().trim(), etStreet1.getText().toString().trim(),
                                etCity.getText().toString().trim(), selectedProv, etPostalCode.getText().toString().trim(), etDob.getText().toString(),
                                imageToStore);
                        Toast.makeText(UpdateUserActivity.this, myUserModel.toString(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } catch (Exception ex) {
                        Toast.makeText(UpdateUserActivity.this, "Something wrong with " + ex.getMessage(), Toast.LENGTH_LONG).show();
                        myUserModel = new UserModel("error", "error", "error", "error", "error", "error", "error",
                                "error", "error", "error", "error", imageToStore);
                    }
                    DataBaseHelper userDataBaseHelper = new DataBaseHelper(UpdateUserActivity.this);
                    boolean success = userDataBaseHelper.updateUserToDb(myUserModel);

                    Toast.makeText(UpdateUserActivity.this, "Is Success? " + success, Toast.LENGTH_SHORT).show();
                }
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

    /*private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return flag = true;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);

                ivUserProfile.setImageBitmap(imageToStore);
                ivUserProfile.setVisibility(View.VISIBLE);
                flag = true;
            }
        } catch (Exception ex){
            Toast.makeText(this, "onActivityResult: " +ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
