package com.gokisoft.c2010g.lesson02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gokisoft.c2010g.R;

public class DetailActivity extends AppCompatActivity {
    TextView fullnameTxt, addressTxt, emailTxt;
    Button editBtn;

    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        std = new Student("TRAN VAN A", "a@gmail.com", "Ha Noi");

        setContentView(R.layout.activity_detail);

        fullnameTxt = findViewById(R.id.ad_fullname);
        addressTxt = findViewById(R.id.ad_address);
        emailTxt = findViewById(R.id.ad_email);
        editBtn = findViewById(R.id.ad_edit_btn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailActivity.this, EditActivity.class);
                i.putExtra("fullname", std.getFullname());
                i.putExtra("address", std.getAddress());
                i.putExtra("email", std.getEmail());

//                startActivity(i);
                startActivityForResult(i, 100);
            }
        });

        updateInfo();
    }

    void updateInfo() {
        fullnameTxt.setText("Ho & Ten: " + std.getFullname());
        addressTxt.setText("Dia chi: " + std.getAddress());
        emailTxt.setText("Email:" + std.getEmail());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode) {
            case 101:
                std.setFullname(data.getStringExtra("fullname"));
                std.setEmail(data.getStringExtra("email"));
                std.setAddress(data.getStringExtra("address"));

                updateInfo();
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}