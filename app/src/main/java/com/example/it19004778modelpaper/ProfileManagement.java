package com.example.it19004778modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it19004778modelpaper.DATABASE.DBHandler;

public class ProfileManagement extends AppCompatActivity {
    EditText username,password,dob;
    Button btnAdd,btnUpdateProfile;
    RadioButton rbMale,rbFemale;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etUserNamePM);
        password = findViewById(R.id.etPasswordPM);
        dob = findViewById(R.id.etDOBPM);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        rbFemale = findViewById(R.id.rbFemale);
        rbMale = findViewById(R.id.rbMale);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler= new DBHandler(getApplicationContext());
                if(rbMale.isChecked()){
                    gender = "male";
                }else{
                    gender = "female";
                }
                long newId = dbHandler.AddInfor(username.getText().toString().trim(),dob.getText().toString().trim(),password.getText().toString().trim(),gender);

                if(newId > 0){
                    Toast.makeText(ProfileManagement.this, "User Added. User Id: "+newId, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(i);
                }else{
                    Toast.makeText(ProfileManagement.this, "Unsuccessful!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}