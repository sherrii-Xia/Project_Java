package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Order o = new Order("Alex");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Order order = new Order("Alex");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
            order = reader.read();
            assertEquals("Alex", order.getName());
            assertEquals(0, order.getNumDish());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Order order = new Order("Alex");
            order.addDish(new Dish("Hotpot", 1, 19.50, "this is hotpot"));
            order.addDish(new Dish("fish",2,20.0, "this is fish"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralOrder.json");
            order = reader.read();
            assertEquals("Alex", order.getName());
            List<Dish> dishes = order.getDishes();
            assertEquals(2, dishes.size());
            checkThingy("Hotpot",1,19.50,"this is hotpot" , dishes.get(0));
            checkThingy("fish",2,20.0, "this is fish", dishes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
