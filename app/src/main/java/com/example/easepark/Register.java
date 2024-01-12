package com.example.easepark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private Button etLogin, etRegister;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRegister = findViewById(R.id.etRegister);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://easepark-database-default-rtdb.firebaseio.com/");
        etRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ada Data yang tidak terisi!", Toast.LENGTH_SHORT).show();
                } else {
                    database = FirebaseDatabase.getInstance().getReference("Users");
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("email").setValue(email);
                    database.child(username).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Data Anda sudah terekam", Toast.LENGTH_SHORT).show();

                    Intent register = new Intent(getApplicationContext(), Login.class);
                    startActivity(register);

                }
            }
        });


    }
}