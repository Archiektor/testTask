package com.archiektor;

import java.util.Map;

/**
 * Created by Archiektor on 01.04.2017.
 */
public class Shop {
    private Map<SportEquipment, Integer> goods;

    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<SportEquipment, Integer> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {

        String output = "";
        for (SportEquipment equipment : goods.keySet()) {
            final Integer count = goods.get(equipment);
            output += equipment.getTitle() + " (" + equipment.getCategory() + ") " + equipment.getPrice() + "$: " + count + " left\n";
        }
        return output;

    }
}
