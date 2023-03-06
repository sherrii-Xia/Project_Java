package persistence;

import model.Dish;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Order order = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/TestReadEmptyOrder.json");
        try {
            Order order = reader.read();
            assertEquals("Alex", order.getName());
            assertEquals(0, order.getNumDish());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/TestReadGeneralOrder.json");
        try {
            Order wr = reader.read();
            assertEquals("Alex", wr.getName());
            List<Dish> dishes = wr.getDishes();
            assertEquals(2, dishes.size());
            checkThingy("Hotpot", 2, 19.5, "There is some food", dishes.get(0));
            checkThingy("Fish", 1, 20.0, "There is some fish ", dishes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}