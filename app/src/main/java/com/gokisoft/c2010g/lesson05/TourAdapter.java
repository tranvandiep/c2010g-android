package com.gokisoft.c2010g.lesson05;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gokisoft.c2010g.R;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourHolder> {
    Activity mActivity;
    List<Tour> tourList;

    public TourAdapter(Activity mActivity, List<Tour> tourList) {
        this.mActivity = mActivity;
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public TourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.item_tour, null);
        TourHolder holder = new TourHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TourHolder holder, int position) {
        Tour tour = tourList.get(position);

        holder.setViewData(tour);
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class TourHolder extends RecyclerView.ViewHolder {
        TextView nameView, priceView, startDateView, endDateView;

        public TourHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.it_name);
            priceView = itemView.findViewById(R.id.it_price);
            startDateView = itemView.findViewById(R.id.it_start_date);
            endDateView = itemView.findViewById(R.id.it_end_date);
        }

        void setViewData(Tour tour) {
            nameView.setText(tour.getName());
            priceView.setText(tour.getPrice() + "");
            startDateView.setText(tour.getStartDate());
            endDateView.setText(tour.getEndDate());
        }
    }
}
