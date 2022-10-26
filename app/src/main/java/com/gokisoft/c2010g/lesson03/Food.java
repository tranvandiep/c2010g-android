package com.gokisoft.c2010g.lesson03;

public class Food {
    String title;
    float price;
    String thumbnail;

    public Food(String title, float price) {
        this.title = title;
        this.price = price;
    }

    public Food(String title, float price, String thumbnail) {
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
