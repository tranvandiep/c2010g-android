package com.gokisoft.c2010g.lesson03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.gokisoft.c2010g.R;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    public static  final String ACTION_ADD_NEW = "ACTION_ADD_NEW";

    ListView mListView;

    List<Food> dataList;

    FoodAdapter adapter;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case ACTION_ADD_NEW:
                    String title = intent.getStringExtra("title");
                    float price = intent.getFloatExtra("price", 0);

                    Food food = new Food(title, price);
                    dataList.add(food);

                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mListView = findViewById(R.id.af_listview);

        dataList = new ArrayList<>();
        dataList.add(new Food("A", 1000));
        dataList.add(new Food("A", 2000));

        adapter = new FoodAdapter(this, dataList);

        mListView.setAdapter(adapter);

        registerForContextMenu(mListView);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ADD_NEW);

        registerReceiver(mReceiver, filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ma_add_new:
                showDialog(null);
                return true;
            case R.id.ma_exist:
                finish();
                return true;
            case R.id.ma_add_new_activity:
                Intent ii = new Intent(FoodActivity.this, EditorFoodActivity.class);
                startActivity(ii);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int position = info.position;
        Food food = dataList.get(position);

        switch (item.getItemId()) {
            case R.id.mli_edit_item:
                showDialog(food);
                return true;
            case R.id.mli_delete_item:
                confirmDelete(position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    void showDialog(Food food) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.editor_food, null);
        builder.setView(view);

        EditText titleTxt = view.findViewById(R.id.ef_title);
        EditText priceTxt = view.findViewById(R.id.ef_price);

        if(food != null) {
            titleTxt.setText(food.getTitle());
            priceTxt.setText(food.getPrice() + "");
        }

        builder.setPositiveButton("Luu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String title = titleTxt.getText().toString();
                    String price = priceTxt.getText().toString();

                    if(food != null) {
                        food.setTitle(title);
                        food.setPrice(Float.parseFloat(price));
                    } else {
                        Food newFood = new Food(title, Float.parseFloat(price));
                        dataList.add(newFood);
                    }

                    adapter.notifyDataSetChanged();
                }
            })
            .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).show();
    }

    void confirmDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xoa du lieu")
                .setMessage("Ban co chac chan muon xoa item nay khong?")
                .setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    @Override
    public void finish() {
        super.finish();

        try {
            unregisterReceiver(mReceiver);
        } catch (Exception e) {}
    }
}