package com.gokisoft.c2010g.lesson03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gokisoft.c2010g.R;

public class EditorFoodActivity extends AppCompatActivity implements View.OnClickListener{
    EditText titleTxt, priceTxt;
    Button saveBtn, closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_food);

        titleTxt = findViewById(R.id.aef_title);
        priceTxt = findViewById(R.id.aef_price);

        saveBtn = findViewById(R.id.aef_save_btn);
        closeBtn = findViewById(R.id.aef_close_btn);

        saveBtn.setOnClickListener(this);
        closeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(saveBtn)) {
            String title = titleTxt.getText().toString();
            float price = Float.parseFloat(priceTxt.getText().toString());

            Intent intent = new Intent();
            intent.setAction(FoodActivity.ACTION_ADD_NEW);
            intent.putExtra("title", title);
            intent.putExtra("price", price);

            sendBroadcast(intent);

            finish();
        } else if(view.equals(closeBtn)) {
            finish();
        }
    }
}