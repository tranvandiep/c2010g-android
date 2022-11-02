package com.gokisoft.c2010g.note;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gokisoft.c2010g.R;
import com.gokisoft.c2010g.lesson03.EditorFoodActivity;
import com.gokisoft.c2010g.lesson03.Food;
import com.gokisoft.c2010g.lesson03.FoodActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    Cursor myCursor;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);

        DBHelper.getInstance(this);

//        NoteModify.insert(new Note("noi dung 1", true, new Date()));
//        NoteModify.insert(new Note("noi dung 2", false, new Date()));
//        NoteModify.insert(new Note("noi dung 3", true, new Date()));

        mListView = findViewById(R.id.anm_listview);
        myCursor = NoteModify.getCursor();

        adapter = new NoteAdapter(this, myCursor);
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
                showNoteDialog(null);
                return true;
            case R.id.ma_exist:
                finish();
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
        Note note = new Note();
        myCursor.moveToPosition(position);
        note.setData(myCursor);

        switch (item.getItemId()) {
            case R.id.mli_edit_item:
                return true;
            case R.id.mli_delete_item:
                NoteModify.delete(note.get_id());

                updateListview();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    void updateListview() {
        myCursor = NoteModify.getCursor();
        adapter.changeCursor(myCursor);

        adapter.notifyDataSetChanged();
    }

    void showNoteDialog(Note note) {
        View view = getLayoutInflater().inflate(R.layout.dialog_note, null);

        TextView titleView = view.findViewById(R.id.dn_title);
        EditText noidungView = view.findViewById(R.id.dn_noidung);
        CheckBox quantrongView = view.findViewById(R.id.dn_quantrong);
        Button cancelBtn = view.findViewById(R.id.dn_cancel);
        Button saveBtn = view.findViewById(R.id.dn_save);

        if(note != null) {
            titleView.setBackgroundColor(Color.GREEN);
            //update noi dung thong tin

        } else {
            titleView.setBackgroundColor(Color.YELLOW);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        Dialog dialog = builder.show();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(note == null) {
                    Note newNote = new Note(noidungView.getText().toString(), quantrongView.isChecked(), new Date());
                    NoteModify.insert(newNote);
                } else {
                    //Sua

                }

                updateListview();
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}