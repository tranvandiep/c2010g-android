package com.gokisoft.c2010g.note;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gokisoft.c2010g.R;

public class NoteAdapter extends CursorAdapter {
    Activity mActivity;

    public NoteAdapter(Activity activity, Cursor c) {
        super(activity, c);

        this.mActivity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.item_note, null);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Note note = new Note();
        note.setData(cursor);

        View quantrongView = view.findViewById(R.id.in_quantrong);
        TextView noidungView = view.findViewById(R.id.in_noidung);
        TextView ngaytaoView = view.findViewById(R.id.in_ngaytao);

        if(note.isQuantrong()) {
            quantrongView.setBackgroundColor(Color.RED);
        } else {
            quantrongView.setBackgroundColor(Color.GREEN);
        }

        noidungView.setText(note.getNoidung());
        ngaytaoView.setText(note.getNgaytaoStr());
    }
}
