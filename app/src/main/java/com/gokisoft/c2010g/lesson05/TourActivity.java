package com.gokisoft.c2010g.lesson05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.gokisoft.c2010g.R;
import com.gokisoft.c2010g.lesson03.EditorFoodActivity;
import com.gokisoft.c2010g.lesson03.Food;
import com.gokisoft.c2010g.lesson03.FoodActivity;
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

public class TourActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<Tour> tourList;
    TourAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        tourList = new ArrayList<>();
//        readSharedPreferences();
        readFile();
//        tourList.add(new Tour("T01", "Da Nang", 2000000, "2022-11-06", "2022-11-09"));
//        tourList.add(new Tour("T02", "Da Nang", 2000000, "2022-11-06", "2022-11-09"));
//        tourList.add(new Tour("T03", "Da Nang", 2000000, "2022-11-06", "2022-11-09"));

        mRecyclerView = findViewById(R.id.at_recyleview);

        adapter = new TourAdapter(this, tourList);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoRequestPermission();
    }

    void autoRequestPermission() {
        if (Build.VERSION.SDK_INT >= 23)
        {
            Log.d(TourActivity.class.getName(), "OKOK > 1321231231");
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {
            Log.d(TourActivity.class.getName(), "1321231231");
            // Code for Below 23 API Oriented Device
            // Do next code
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(TourActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            Log.d(TourActivity.class.getName(), "OK");
            return true;
        } else {
            Log.d(TourActivity.class.getName(), "Not OK");
            return false;
        }
    }

    private void requestPermission() {
        Log.d(TourActivity.class.getName(), "OK 12345");
        if (ActivityCompat.shouldShowRequestPermissionRationale(TourActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.d(TourActivity.class.getName(), "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.");
        } else {
            ActivityCompat.requestPermissions(TourActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.e(TourActivity.class.getName(), "Permission Granted, Now you can use local drive .");
        } else {
            Log.e(TourActivity.class.getName(), "Permission Denied, You cannot use local drive .");
        }
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
                showEditorDialog();
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

    void showEditorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.editor_tour, null);
        builder.setView(view);

        EditText nameTxt = view.findViewById(R.id.et_name);
        EditText priceTxt = view.findViewById(R.id.et_price);
        EditText startDateTxt = view.findViewById(R.id.et_start_date);
        EditText endDateTxt = view.findViewById(R.id.et_end_date);

        builder.setPositiveButton("Luu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = nameTxt.getText().toString();
                        String price = priceTxt.getText().toString();
                        String startDate = startDateTxt.getText().toString();
                        String endDate = endDateTxt.getText().toString();

                        Tour tour = new Tour(name, "Ha Noi", Float.parseFloat(price), startDate, endDate);

                        tourList.add(tour);

                        adapter.notifyDataSetChanged();

//                        saveSharedPreferences();
                        saveFile();
                    }
                })
                .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    void readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = openFileInput("tours.dat");
            ois = new ObjectInputStream(fis);

            tourList = (List<Tour>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void saveFile() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = openFileOutput("tours.dat", MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(tourList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("C2010G", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Chuyen array tourList -> json string
        Gson gson = new Gson();
        String json = gson.toJson(tourList);

        editor.putString("tourList", json);

        editor.commit();
    }

    void readSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("C2010G", MODE_PRIVATE);

        String json = sharedPreferences.getString("tourList", null);
        if(json != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Tour>>() {}.getType();

            tourList = gson.fromJson(json, listType);
        }
    }
}