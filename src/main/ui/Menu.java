package ui;

import model.Dish;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    //TODO: Instansite a full menu, with the each dish Descrpition
    private List<Dish> totalDish;
    private List<Dish> boilingPot;
    private List<Dish> meats;
    private List<Dish> leafVegetables;

    public List<Dish> settingUpDish() {
        this.setBoilingPot();
        this.setleafVegetables();
        this.setMeats();

        totalDish = new ArrayList<>();

        totalDish.addAll(boilingPot);
        totalDish.addAll(meats);
        totalDish.addAll(leafVegetables);
        return totalDish;

    }

    public void displayMenu() {
        this.settingUpDish();
        System.out.println("\n Categories: Boiling Pot");
        int key = 1;
        for (Dish d : boilingPot) {
            System.out.println("\t" + "NO." +  key + " " + d.getName() + " $" + d.getPrice());
            key++;
        }
        System.out.println("\n Categories: Meats");
        for (Dish d : meats) {
            System.out.println("\t" + "NO." + key + " " + d.getName() + " $" + d.getPrice());
            key++;
        }

        System.out.println("\n Categories: Leaf Vegetable");
        for (Dish d : leafVegetables) {
            System.out.println("\t" + "NO." +  key + " " + d.getName() + " $" + d.getPrice());
            key++;
        }
    }

    public void setBoilingPot() {
        this.boilingPot = new ArrayList<>();
        Dish d1 = new Dish("Sichuan Spicy Hotpot", 1, 24.0);
        Dish d2 = new Dish(" Pork Stomach and Chicken Soup", 1, 24.0);
        Dish d3 = new Dish("Sichuan Pickles Hot Pot", 1, 18.0);
        Dish d4 = new Dish("Tomato Soup Hot Pot", 1, 20.0);
        boilingPot.add(d1);
        boilingPot.add(d2);
        boilingPot.add(d3);
        boilingPot.add(d4);
    }

//    public void  setdriedPot() {
//        this.driedPot = new ArrayList<>();
//    }

    public void setMeats() {
        this.meats = new ArrayList<>();
        Dish d5 = new Dish("Iberico Pork", 1, 24.0);
        Dish d6 = new Dish("Aussie Sliced Lamb Shoulder", 1, 24.0);
        Dish d7 = new Dish("Sliced US Beef", 1, 28.0);
        Dish d8 = new Dish("Signature Beef", 1, 38.0);
        meats.add(d5);
        meats.add(d6);
        meats.add(d7);
        meats.add(d8);
    }

    public void setleafVegetables() {
        this.leafVegetables = new ArrayList<>();
        Dish d9 = new Dish("Vegetable Combo", 1, 14.0);
        Dish d10 = new Dish("Cilantro", 1, 12.0);
        Dish d11 = new Dish("Iceberg Lettuce", 1, 12.0);
        Dish d12 = new Dish("Spinach", 1, 12.0);

        leafVegetables.add(d9);
        leafVegetables.add(d10);
        leafVegetables.add(d11);
        leafVegetables.add(d12);

    }


}
