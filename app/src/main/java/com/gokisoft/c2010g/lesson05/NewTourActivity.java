package com.gokisoft.c2010g.lesson05;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gokisoft.c2010g.R;
import com.gokisoft.c2010g.lesson03.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewTourActivity extends AppCompatActivity {
    ListView mListView;
    TourAdapter2 adapter;
    Cursor currentCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_new);

        DBHelper.getInstance(this);

        //Fake -> 1 lan run app
//        TourDAO.insert(new Tour("T01", "Da Nang", 2000000, "2022-11-06", "2022-11-09"));
//        TourDAO.insert(new Tour("T02", "Da Nang", 2000000, "2022-11-06", "2022-11-09"));
//        TourDAO.insert(new Tour("T03", "Da Nang", 2000000, "2022-11-06", "2022-11-09"));

        currentCursor = TourDAO.getCursor();
        adapter = new TourAdapter2(this, currentCursor);

        mListView = findViewById(R.id.atn_listview);
        mListView.setAdapter(adapter);

        registerForContextMenu(mListView);
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
                showEditorDialog(null);
                return true;
            case R.id.ma_exist:
                finish();
                return true;
            case R.id.ma_add_new_activity:
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
        currentCursor.moveToPosition(position);
        Tour tour = new Tour();
        tour.setData(currentCursor);

        switch (item.getItemId()) {
            case R.id.mli_edit_item:
                showEditorDialog(tour);
                return true;
            case R.id.mli_delete_item:
                TourDAO.delete(tour.getId());
                updateCursor();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    void updateCursor() {
        currentCursor = TourDAO.getCursor();
        adapter.changeCursor(currentCursor);
        adapter.notifyDataSetChanged();
    }

    void showEditorDialog(Tour tour) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.editor_tour, null);
        builder.setView(view);

        EditText nameTxt = view.findViewById(R.id.et_name);
        EditText priceTxt = view.findViewById(R.id.et_price);
        EditText startDateTxt = view.findViewById(R.id.et_start_date);
        EditText endDateTxt = view.findViewById(R.id.et_end_date);

        if(tour != null) {
            nameTxt.setText(tour.getName());
            priceTxt.setText(tour.getPrice() + "");
            startDateTxt.setText(tour.getStartDate());
            endDateTxt.setText(tour.getEndDate());
        }

        builder.setPositiveButton("Luu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = nameTxt.getText().toString();
                        String price = priceTxt.getText().toString();
                        String startDate = startDateTxt.getText().toString();
                        String endDate = endDateTxt.getText().toString();

                        if(tour != null) {
                            tour.setName(name);
                            tour.setPrice(Float.parseFloat(price));
                            tour.setStartDate(startDate);
                            tour.setEndDate(endDate);

                            TourDAO.update(tour);
                        } else {
                            Tour newTour = new Tour(name, "Ha Noi", Float.parseFloat(price), startDate, endDate);

                            TourDAO.insert(tour);
                        }

                        updateCursor();
                    }
                })
                .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }
}