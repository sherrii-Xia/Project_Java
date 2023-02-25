package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class DishTest {
    Dish testDish;
    Dish testDish1;
    ArrayList<String> ingret;
    @BeforeEach
    public void setup() {
        testDish = new Dish("Dish 1", 1, 12.5);
        ingret = new ArrayList<String>();
        ingret.add("Potato");
        ingret.add("Flour");
        testDish1 = new Dish("Fires", 1, 12.5, "Sour", ingret);
    }
    @Test
    public void dishTest() {
        assertEquals("Dish 1",testDish.getName());
        assertEquals(1,testDish.getCount());
        assertEquals(12.5,testDish.getPrice());
    }

    @Test
    public void DishOverloadTest() {
        testDish1 = new Dish("Fires", 1, 12.5, "Sweet", ingret);
        assertEquals("Fires",testDish1.getName());
        assertEquals(1,testDish1.getCount());
        assertEquals(12.5,testDish1.getPrice());
        assertEquals("Sweet",testDish1.getFlavor());
        assertEquals(ingret,testDish1.getIngredients());

    }

    @Test
    public void getNameTest() {
        assertEquals("Dish 1", testDish.getName());
    }

    @Test
    public void getCountTest() {
        assertEquals(1,testDish.getCount());
    }

    @Test
    public void getPrice() {
        assertEquals(12.5, testDish.getPrice());
    }

    @Test
    public void getFlavor() {
        assertEquals("Sour", testDish1.getFlavor());

    }

    @Test
    public void getIngredients() {
        assertEquals(ingret, testDish1.getIngredients());
    }

    @Test
    public void setCountTest() {
       testDish.setCount(10);
       assertEquals(10, testDish.getCount());

    }
    @Test
    public void setIngredientsTest() {
        ArrayList<String> empty = new ArrayList<String>();
        testDish1.setIngredients(empty);
        assertEquals(empty, testDish1.getIngredients());
    }


    @Test
    public void setFlavorTest() {
        testDish1.setFlavor("Spicy");
        assertEquals("Spicy",testDish1.getFlavor());


    }

}