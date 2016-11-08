package com.weatherapp.weatherapp.presentation.home;

import java.util.List;

/**
 * Model for data to fill home screen
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomeModel {

    private String town;
    private List<HomeItem> listForest;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<HomeItem> getListForest() {
        return listForest;
    }

    public void setListForest(List<HomeItem> listForest) {
        this.listForest = listForest;
    }
}
