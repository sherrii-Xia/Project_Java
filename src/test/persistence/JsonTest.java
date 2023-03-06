package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String name, int count, double price, String ingret, Dish dish) {
        assertEquals(name, dish.getName());
        assertEquals(count, dish.getCount());
        assertEquals(ingret, dish.getIngredients());
    }
}
