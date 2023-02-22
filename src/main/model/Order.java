package model;

import java.util.ArrayList;

public class Order {
    private int account;
    private String customer;
    private ArrayList<Dish> dishes;

    // REQUIRES: TODO
    // MODIFIES: this
    // EFFECTS: create a new empty order with given customer name.
    public Order(String name) {
        this.customer = name;
        dishes = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: if the dish is already exits in the order, count for that dish add
    //          1
    //          otherwise add that the new dish in to the orders.
    public void addDish(Dish dish) {
        if (dishes.size() == 0 || !this.containDish(dish)) {
            dishes.add(dish);
        } else {
            int index = 0;
            for (Dish current : dishes) {
                if (current.getName() == dish.getName()) {
                    int currentCount = current.getCount();
                    currentCount++;
                    current.setCount(currentCount);
                }
                index++;
            }
        }

    }

    // REQUIRES: Dish has to be exits in the dishes list
    // MODIFIES: this
    // EFFECTS: if the dish is already exits in the order with count >= 2, then
    //          the count for that dish will be subtract by 1,
    //          otherwise remove the dish in the order.
    public boolean removeDish(Dish dish) {
        int index = 0;
        boolean success = false;
        for (Dish current : dishes) {
            if (current.getName() == dish.getName()) {
                int currentCount = current.getCount();
                success = true;
                if (currentCount == 1) {
                    dishes.remove(index);

                } else {
                    currentCount--;
                    current.setCount(currentCount);
                }
            }
            index++;
        }
        return success;

    }

    public void displayOrder() {
        System.out.println("The current summary of the Order");
        System.out.printf("%-35s%-10s\n","Dish", "Cost");
        for (Dish current : dishes) {
            current.printDish();
        }

        System.out.println("\nThe total Amount : " + "$" + getTotalAmount());
    }

    // REQUIRES: Dish have to be exists in the dishes list， num >= 1
    // MODIFIES: this
    // EFFECTS: modify the count of given dish in the list.
    public void setDish(Dish dish, int num) {
        int index = 0;
        for (Dish current : dishes) {
            if (current.getName() == dish.getName()) {
                current.setCount(num);
            }
            index++;
        }
    }


    // MODIFIES: this
    // EFFECTS: get the total amount of orders, if there is no dish return 0.
    public double getTotalAmount() {
        double currentSum = 0.0;
        if (dishes.size() == 0) {
            return currentSum;
        }
        for (Dish current : dishes) {
            currentSum += (double) current.getCount() * current.getPrice();
        }
        return currentSum;

    }

    public String getName() {
        return this.customer;
    }


    public int getNumDish() {
        return dishes.size();
    }

    public boolean containDish(Dish d) {
        for (Dish current : dishes) {
            if (current.getName() == d.getName()) {
                return true;
            }
        }
        return false;
    }
}
