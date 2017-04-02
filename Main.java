package com.archiektor;

import com.google.gson.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final String JSON_PATH = "E:/Epam/untitled/src/com/archiektor/goods.json";

    private static Map<SportEquipment, Integer> sportMap;
    private static Gson gson;


    public static void main(String[] args) throws IOException {
        /*****
         * Open file with help of GSON and initializate sportMap with data
         */
        initialaze();

        /*****
         * Show dialog messages on console; method №2 has empty realization (yet) and doesn't do anything
         */
        while (true) {
            System.out.print("Enter 1 to grab equipment;\n2 - to return equipment;\n3 - see available goods;\n0 - to exit the programm");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            final String equipment = br.readLine();
            switch (equipment) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    /**
                     * method №2 has empty realization (yet) and doesn't do anything
                     */
                    rentItem();
                    break;
                case "2":
                    /**
                     * method №2 has empty realization (yet) and doesn't do anything
                     */
                    returnItem();
                    break;
                case "3":
                    showEquipment();
                    break;
                default:
                    System.out.println("Invalid input. Try again");
            }
        }
    }

    private static void showEquipment() {
        FileReader reader = null;
        BufferedReader bufferedReader;

        gson = new Gson();
        sportMap = new HashMap<>();
        Shop shop = new Shop();


        try {
            reader = new FileReader(JSON_PATH);
            bufferedReader = new BufferedReader(reader);

            JsonArray array = gson.fromJson(bufferedReader, JsonArray.class);

            for (JsonElement obj : array) {
                SportEquipment eq = gson.fromJson(obj.getAsJsonObject().get("product"), SportEquipment.class);
                Integer productCount = obj.getAsJsonObject().get("product_count").getAsInt();
                sportMap.put(eq, productCount);
            }

        } catch (IOException e) {
            System.out.println("IO Error :  " + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException е2) {
                System.out.println("IO FileCLose : ");
            }
        }

        shop.setGoods(sportMap);
        System.out.println("Available equipment: ");
        System.out.println(shop.toString());
    }


    private static void initialaze() {
        FileReader reader = null;
        BufferedReader bufferedReader;

        gson = new Gson();
        sportMap = new HashMap<>();
        Shop shop = new Shop();


        try {
            reader = new FileReader(JSON_PATH);
            bufferedReader = new BufferedReader(reader);

            JsonArray array = gson.fromJson(bufferedReader, JsonArray.class);

            for (JsonElement obj : array) {
                SportEquipment eq = gson.fromJson(obj.getAsJsonObject().get("product"), SportEquipment.class);
                Integer productCount = obj.getAsJsonObject().get("product_count").getAsInt();
                sportMap.put(eq, productCount);
            }

        } catch (IOException e) {
            System.out.println("IO Error :  " + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException е2) {
                System.out.println("IO FileCLose : ");
            }
        }

        shop.setGoods(sportMap);
        System.out.println("Available equipment: ");
        System.out.println(shop.toString());
    }

    private static void rentItem() throws IOException {
        System.out.println("Enter the name of equipment:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final String name = bufferedReader.readLine();

        RentUnit unit = new RentUnit();
        SportEquipment[] array;

        boolean isInStore = false;

        for (SportEquipment equipmentKey : sportMap.keySet()) {
            if (equipmentKey.getTitle().equals(name)) {
                isInStore = true;
                final Integer availableCount = sportMap.get(equipmentKey);
                System.out.println("Enter number of needed equipment:");
                final String countString = bufferedReader.readLine();
                Integer count = Integer.valueOf(countString);
                /**
                 * check if 'count' from console is right
                 */
                if (count > 0) {
                    /**
                     * there could be check 'countString' <=3 else print on screen error p.5 for one client ?
                     */
                    if (availableCount >= count) {
                        array = new SportEquipment[count];
                        for (int i = 0; i < array.length; i++) {
                            array[i] = equipmentKey;
                        }
                        unit.setUnits(array);
                        sportMap.put(equipmentKey, availableCount - count);
                        /**
                         * reverse method for saving data to json
                         */
                        saveMapToFile(sportMap);
                        break;
                    } else {
                        System.out.println("Not enough equipment value; " + "Have only " + availableCount + " number");
                    }
                } else {
                    System.out.println("Invalid number");
                }
            }
        }

        if (!isInStore) {
            System.out.println("We don't have such equipment");
        }

    }

    private static void saveMapToFile(Map<SportEquipment, Integer> map) {

        Gson gson = new GsonBuilder().create();
        FileWriter out = null;
        BufferedWriter bufferedWriter = null;

        try {
            out = new FileWriter(JSON_PATH);
            bufferedWriter = new BufferedWriter(out);
            JsonArray jsonArray = new JsonArray();
            for (SportEquipment equipmentKey : map.keySet()) {
                JsonObject object = new JsonObject();
                object.addProperty("product_count", map.get(equipmentKey));
                object.add("product", gson.toJsonTree(equipmentKey));

                jsonArray.add(object);
            }

            bufferedWriter.write(jsonArray.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    /**
                     * forces out the jsonArray to FileWriter
                     */
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException е2) {
                System.out.println("IO FileCLose :");
            }
        }
    }

    private static void returnItem() {

    }
}

