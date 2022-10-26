package com.gokisoft.c2010g.lesson03;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokisoft.c2010g.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    Activity mActivity;
    List<Food> dataList;

    public FoodAdapter(Activity mActivity, List<Food> dataList) {
        this.mActivity = mActivity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = mActivity.getLayoutInflater().inflate(R.layout.item_food, null);

        TextView titleView = view.findViewById(R.id.if_title);
        TextView priceView = view.findViewById(R.id.if_price);
        ImageView imageView = view.findViewById(R.id.if_photo);

        titleView.setText(dataList.get(position).getTitle());
        priceView.setText(dataList.get(position).getPrice() + "");

        Picasso.with(mActivity)
                .load(dataList.get(position).getThumbnail())
                .into(imageView);

        return view;
    }
}
