package com.archiektor;

/**
 * Created by Archiektor on 01.04.2017.
 */
public class RentUnit {

    private SportEquipment[] units;

    public SportEquipment[] getUnits() {
        return units;
    }

    public void setUnits(SportEquipment[] units) {
        this.units = units;
    }

    @Override
    public String toString() {

        String output = "";
        for (int i = 0; i < units.length; i++) {
            output += units[i].toString() + "\n";
        }
        return output;
    }
}
