package com.weatherapp.weatherapp.presentation.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weatherapp.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for recycleview
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeItem> listItems = new ArrayList<>();

    public HomeAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // only one type here, just inflate the layout (no switch)
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.listitem_home, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;

        HomeItem item = listItems.get(position);

        viewHolder.onBind(item);

    }

    @Override
    public int getItemCount() {
        if ( listItems != null && !listItems.isEmpty()) {
            return listItems.size();
        }
        return 0;
    }

    public void setListItems(List<HomeItem> listItems) {
        this.listItems = listItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.listitem_home_time) TextView timeTextView;
        @BindView(R.id.listitem_home_description) TextView descriptionTextView;
        @BindView(R.id.listitem_home_temperature) TextView temperatureTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void onBind(final HomeItem item) {

            if ( item != null ) {
                if ( item.getTime() != null ) {
                    timeTextView.setText( item.getTime());
                }

                if ( item.getDescription() != null) {
                    descriptionTextView.setText(item.getDescription());

                }

                if ( item.getTemperature() != null) {
                    temperatureTextView.setText(item.getTemperature());
                }


            }
        }
    }

}
