package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents an order for the customer using Ordering Application.
 */

public class Order {
    private String customer;
    private ArrayList<Dish> dishes;


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
                if (current.getName().equals(dish.getName())) {
                    int currentCount = current.getCount();
                    currentCount++;
                    current.setCount(currentCount);
                }
                index++;
            }
        }
        EventLog.getInstance().logEvent(new Event("Dish: " + dish.getName() + " is added to order"));
    }

    // REQUIRES: Dish has to be exits in the dishes list
    // MODIFIES: this
    // EFFECTS: if the dish is already exits in the order with count >= 2, then
    //          the count for that dish will be subtracted by 1,
    //          otherwise remove the dish in the order.
    public boolean removeDish(Dish dish) {
        int index = 0;
        boolean success = false;
        int size = dishes.size();
        for (Dish current : dishes) {
            if (current.getName().equals(dish.getName())) {
                int currentCount = current.getCount();
                success = true;
                if (currentCount == 1) {
                    dishes.remove(index);

                } else {
                    currentCount--;
                    current.setCount(currentCount);
                }
                break;
            }
            index++;
        }
        if (success) {
            EventLog.getInstance().logEvent(new Event("Dish: " + dish.getName() + " is removed  from order"));
        }
        return success;

    }


    // REQUIRES: Dish have to be exists in the dishes list， num >= 1
    // MODIFIES: this
    // EFFECTS: modify the count of given dish in the list.
    public void setDish(Dish dish, int num) {
        int index = 0;
        for (Dish current : dishes) {
            if (current.getName().equals(dish.getName())) {
                current.setCount(num);
            }
            index++;
        }
        EventLog.getInstance().logEvent(new Event("Dish: " + dish.getName() + "been set to count" + num));
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

     //MODIFIES: this
     //EFFECTS: clean up the selected dish in the Order
    public void cleanOrder(String name) {
        EventLog.getInstance().logEvent(new Event("Order is cleaned"));
        dishes = new ArrayList<>();
        this.customer = name;


    }

    // EFFECTS: get the name of the customer
    public String getName() {
        return this.customer;
    }

    //EFFECTS: get the totoal number of the dish in the order
    public int getNumDish() {
        return dishes.size();
    }

    //EFFECTS: get the dishes in the order
    public List<Dish> getDishes() {
        return this.dishes;
    }

    //EFFECTS: return true if the dish in the order, false otherwise
    public boolean containDish(Dish d) {
        for (Dish current : dishes) {
            if (current.getName() == d.getName()) {
                return true;
            }

        }
        return false;
    }


    // EFFECTS: return the JSOMObject corresponds to current Order
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("customer", customer);
        json.put("dishes", dishesToJson());
        return json;
    }

    // EFFECTS: return the JSONArray corresponds to current dishes in Order
    private JSONArray dishesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Dish d : dishes) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }
}
