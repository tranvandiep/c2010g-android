package com.gokisoft.c2010g.lesson03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gokisoft.c2010g.R;

import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity {
    ListView mListView;
    Button addBtn;

    List<String> dataList;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        mListView = findViewById(R.id.as_listview);
        addBtn = findViewById(R.id.as_add_new);

        dataList = new ArrayList<>();
        dataList.add("Song 1");
        dataList.add("Song 2");
        dataList.add("Song 3");
        dataList.add("Song 4");
        dataList.add("Song 5");
        dataList.add("Song 6");
        dataList.add("Song 7");
        dataList.add("Song 8");

        adapter = new ArrayAdapter<>(this, R.layout.item_song, R.id.is_title, dataList);

        mListView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SongActivity.this, EditSongActivity.class);
                startActivityForResult(i, 100);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String title = dataList.get(position);

                Toast.makeText(SongActivity.this, title, Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(mListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mli_edit_item:
                String title = dataList.get(info.position);

                Intent i = new Intent(SongActivity.this, EditSongActivity.class);
                i.putExtra("title", title);
                i.putExtra("position", info.position);
                startActivityForResult(i, 100);
                return true;
            case R.id.mli_delete_item:
                dataList.remove(info.position);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode) {
            case 101:
                String title = data.getStringExtra("title");
                int position = data.getIntExtra("position", -1);

                if(position >= 0) {
                    dataList.set(position, title);
                } else {
                    dataList.add(title);
                }

                adapter.notifyDataSetChanged();
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}