package com.gokisoft.c2010g.lesson02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gokisoft.c2010g.R;

public class EditActivity extends AppCompatActivity {
    EditText fullnameTxt, emailTxt, addressTxt;
    Button saveBtn, closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        fullnameTxt = findViewById(R.id.ae_fullname);
        emailTxt = findViewById(R.id.ae_email);
        addressTxt = findViewById(R.id.ae_address);
        saveBtn = findViewById(R.id.ae_save_data_btn);
        closeBtn = findViewById(R.id.ae_cancel_btn);

        String fullname = getIntent().getStringExtra("fullname");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");

        fullnameTxt.setText(fullname);
        addressTxt.setText(address);
        emailTxt.setText(email);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = fullnameTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String address = addressTxt.getText().toString();

                Intent i = new Intent();
                i.putExtra("fullname", fname);
                i.putExtra("email", email);
                i.putExtra("address", address);

                setResult(101, i);
                finish();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}