package com.gokisoft.c2010g.lesson05;

import java.io.Serializable;

public class Tour implements Serializable {
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
