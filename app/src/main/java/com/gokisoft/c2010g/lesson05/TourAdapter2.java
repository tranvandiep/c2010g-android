package com.gokisoft.c2010g.lesson05;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gokisoft.c2010g.R;

public class TourAdapter2 extends CursorAdapter {
    Activity mActivity;

    public TourAdapter2(Activity activity, Cursor c) {
        super(activity, c);
        this.mActivity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.item_tour, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameView = view.findViewById(R.id.it_name);
        TextView priceView = view.findViewById(R.id.it_price);
        TextView startDateView = view.findViewById(R.id.it_start_date);
        TextView endDateView = view.findViewById(R.id.it_end_date);

        Tour tour = new Tour();
        tour.setData(cursor);

        nameView.setText(tour.getName());
        priceView.setText(tour.getPrice() + "");
        startDateView.setText(tour.getStartDate());
        endDateView.setText(tour.getEndDate());
    }
}
