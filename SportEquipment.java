package com.archiektor;

import java.util.Locale;

/**
 * Created by Archiektor on 01.04.2017.
 */
public class SportEquipment {

    private String category;
    private String title;
    private int price;

    public SportEquipment(String category, String title, int price) {
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n category : " + category + "; " + "title : " + title + "; " + "price :" + price + "; ";
    }
}
