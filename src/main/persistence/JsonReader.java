package persistence;


import model.Dish;
import model.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Order from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Order read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Order parseWorkRoom(JSONObject jsonObject) {
        String customer = jsonObject.getString("customer");
        Order order = new Order(customer);
        addDishes(order, jsonObject);
        return order;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addDishes(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dishes");
        for (Object json : jsonArray) {
            JSONObject nextDish = (JSONObject) json;
            addDish(order, nextDish);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addDish(Order order, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int count = jsonObject.getInt("count");
        double price = jsonObject.getDouble("price");
        String ingret = jsonObject.getString("ingredients");
        Dish dish = new Dish(name, count, price, ingret);
        order.addDish(dish);
    }
}