package com.gokisoft.c2010g.lesson05;

import android.database.Cursor;

import java.io.Serializable;

public class Tour implements Serializable {
    int _id;
    String name;
    String address;
    float price;
    String startDate;
    String endDate;

    public Tour() {
    }

    public Tour(String name, String address, float price, String startDate, String endDate) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setData(Cursor cursor) {
        this._id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        this.name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        this.address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
        this.price = cursor.getFloat(cursor.getColumnIndexOrThrow("price"));
        this.startDate = cursor.getString(cursor.getColumnIndexOrThrow("start_date"));
        this.endDate = cursor.getString(cursor.getColumnIndexOrThrow("end_date"));
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
