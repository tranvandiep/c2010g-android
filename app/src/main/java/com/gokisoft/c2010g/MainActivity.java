package com.gokisoft.c2010g;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameTxt, addressTxt;
    Button saveDataBtn, resetFormBtn, openBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTxt = findViewById(R.id.am_username);
        addressTxt = findViewById(R.id.am_address);

        saveDataBtn = findViewById(R.id.am_save_data_btn);
        resetFormBtn = findViewById(R.id.am_reset_form_btn);
        openBtn = findViewById(R.id.am_open_btn);

        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTxt.getText().toString();
                String address = addressTxt.getText().toString();

                Toast.makeText(MainActivity.this, username + ", " + address, Toast.LENGTH_SHORT).show();
            }
        });

        resetFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameTxt.setText("");
                addressTxt.setText("");
            }
        });

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);
                startActivity(i);
            }
        });
    }
}