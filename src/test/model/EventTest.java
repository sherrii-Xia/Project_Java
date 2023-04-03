package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Dish: BEFF is added");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Dish: BEFF is added", e.getDescription()); //(1)
        assertEquals(d.getDay(), e.getDate().getDay()); //(2)
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Dish: BEFF is added", e.toString());
    }

    @Test
    public void testHashCode() {
        assertEquals(Event.HASH_CONSTANT * e.getDate().hashCode() + e.getDescription().hashCode(), e.hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse(e.equals(null));
        assertFalse(e.equals(d));
    }
}

