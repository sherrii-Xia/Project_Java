package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {

    Order o1;
    Order emptyOrder;
    Order DuplicateOrder;
    Dish tomato, fires, hamberger;

    @BeforeEach
    public void setup() {
        tomato = new Dish("tomato stew", 1, 19.5);
        fires = new Dish("french fires", 1, 5.0);
        hamberger = new Dish("Hamburger", 2, 12.0);
        o1 = new Order("Order 1");
        emptyOrder = new Order("Empty Order!");
        DuplicateOrder = new Order("Duplicated! ");
        DuplicateOrder.addDish(tomato);
        DuplicateOrder.addDish(hamberger);

    }

    @Test
    public void orderTest() {
        assertEquals("Order 1", o1.getName());


    }

    // MODIFIES: this
    // EFFECTS: if the dish is already exits in the order, count for that dish add
    //          1
    //          otherwise add that the new dish in to the orders.
    @Test
    public void addDishTestOneDish() {
        emptyOrder.addDish(tomato);
        assertEquals(19.5, emptyOrder.getTotalAmount());
        emptyOrder.addDish(tomato);
        assertEquals(19.5 * 2, emptyOrder.getTotalAmount());
        assertEquals(1, emptyOrder.getNumDish());
        emptyOrder.addDish(tomato);
        assertEquals(19.5 * 3, emptyOrder.getTotalAmount());
        assertEquals(1, emptyOrder.getNumDish());


    }

    @Test
    public void addDishTestMultipleDish() {
        emptyOrder.addDish(tomato);
        assertEquals(19.5, emptyOrder.getTotalAmount());
        emptyOrder.addDish(fires);
        assertEquals(19.5 + 5.0, emptyOrder.getTotalAmount());
        assertEquals(2, emptyOrder.getNumDish());
        emptyOrder.addDish(hamberger);
        assertEquals(3,emptyOrder.getNumDish());


    }

    // REQUIRES: Dish has to be exits in the dishes list
    // MODIFIES: this
    // EFFECTS: if the dish is already exits in the order with count >= 2, then
    //          the count for that dish will be subtract by 1,
    //          otherwise remove the dish in the order.
    @Test
    public void removeDishRemoveOne() {

        DuplicateOrder.removeDish(tomato);
        assertEquals( 12.0 * 2.0, DuplicateOrder.getTotalAmount());
        assertEquals(1, DuplicateOrder.getNumDish());
        boolean r = DuplicateOrder.removeDish(tomato);
        assertFalse(r);


    }

    @Test
    public void removeDishRemoveMultiple() {
        boolean r = DuplicateOrder.removeDish(tomato);
        assertEquals( 12.0*2.0, DuplicateOrder.getTotalAmount());
        assertEquals(1, DuplicateOrder.getNumDish());
        DuplicateOrder.removeDish(hamberger);
        assertEquals(12.0, DuplicateOrder.getTotalAmount());
        assertEquals(1, DuplicateOrder.getNumDish());


    }

    // REQUIRES: Dish have to be exists in the dishes list， num >= 1
    // MODIFIES: this
    // EFFECTS: modify the count of given dish in the list.
    @Test
    public void setDishTest() {
        DuplicateOrder.setDish(tomato, 10);
        assertEquals(19.5 * 10 + 12.0*2, DuplicateOrder.getTotalAmount());
        assertEquals(2, DuplicateOrder.getNumDish());


    }


    @Test
    public void getTotalAmount() {
        assertEquals(0.0, emptyOrder.getTotalAmount());
        emptyOrder.addDish(tomato);
        assertEquals(19.5,emptyOrder.getTotalAmount());
        emptyOrder.addDish(tomato);
        assertEquals(19.5 * 2, emptyOrder.getTotalAmount());
        assertEquals(19.5 * 2 + 12.0*2.0, DuplicateOrder.getTotalAmount());


    }
    @Test
    public void getDishesTest() {
        List<Dish> r1 = new ArrayList<>();
        r1.add(tomato);
        r1.add(hamberger);
        assertEquals(r1, DuplicateOrder.getDishes());
    }


}
