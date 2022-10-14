package com.gokisoft.c2010g.lesson03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.gokisoft.c2010g.R;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    ListView mListView;

    List<Food> dataList;

    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mListView = findViewById(R.id.af_listview);

        dataList = new ArrayList<>();
        dataList.add(new Food("A", 1000));
        dataList.add(new Food("A", 2000));
        dataList.add(new Food("A", 6000));
        dataList.add(new Food("A", 2000));
        dataList.add(new Food("A", 8000));

        adapter = new FoodAdapter(this, dataList);

        mListView.setAdapter(adapter);
    }
}