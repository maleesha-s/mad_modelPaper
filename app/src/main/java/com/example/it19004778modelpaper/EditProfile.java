package com.example.it19004778modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it19004778modelpaper.DATABASE.DBHandler;

import java.util.List;


public class EditProfile extends AppCompatActivity {
    EditText username,password,dob;
    Button btnSearch,btnEdit,btnDelete;
    RadioButton rbMale,rbFemale;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.etUserNameEP);
        password = findViewById(R.id.etPasswordEp);
        dob = findViewById(R.id.etDOBEP);
        btnSearch = findViewById(R.id.btnSearch);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        rbFemale = findViewById(R.id.rbFemaleEp);
        rbMale = findViewById(R.id.rbMaleEP);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(username.getText().toString().trim());

                if(user.isEmpty()){
                    Toast.makeText(EditProfile.this, "No users", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }else{
                    Toast.makeText(EditProfile.this, "User "+user.get(0).toString() + " found!", Toast.LENGTH_SHORT).show();
                    username.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).toString());
                    if(user.get(3).toString().equals("male")){
                        rbMale.setChecked(true);
                    }else{
                        rbFemale.setChecked(true);
                    }
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                if(rbMale.isChecked()){
                    gender = "male";
                }else{
                    gender = "female";
                }
                boolean status = dbHandler.updateInfo(username.getText().toString().trim(),dob.getText().toString().trim(),password.getText().toString().trim(),gender);
                if(status){
                    Toast.makeText(EditProfile.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditProfile.this, "Unsuccessfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(username.getText().toString().trim());
                Toast.makeText(EditProfile.this, "User Deleted!", Toast.LENGTH_SHORT).show();
                username.setText(null);
                dob.setText(null);
                password.setText(null);
                rbMale.setChecked(false);
                rbFemale.setChecked(false);
            }
        });
    }
}