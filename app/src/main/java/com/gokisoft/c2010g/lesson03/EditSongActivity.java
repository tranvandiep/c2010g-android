package com.gokisoft.c2010g.lesson03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gokisoft.c2010g.R;

public class EditSongActivity extends AppCompatActivity {
    EditText titleTxt;
    Button saveBtn, closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        String title = getIntent().getStringExtra("title");
        int position = getIntent().getIntExtra("position", -1);

        titleTxt = findViewById(R.id.aes_title);
        saveBtn = findViewById(R.id.aes_save_btn);
        closeBtn = findViewById(R.id.aes_close);

        titleTxt.setText(title);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleTxt.getText().toString();

                Intent i = new Intent();
                i.putExtra("title", title);
                i.putExtra("position", position);

                setResult(101, i);
                finish();
            }
        });
    }
}