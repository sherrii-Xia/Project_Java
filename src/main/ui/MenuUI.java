package ui;

import model.Dish;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents Mean Panel of GUI.
 */

public class MenuUI extends JPanel {
    Menu menu;
    private List<DishButton> buttons = new ArrayList<>();
    List<JLabel> labels = new ArrayList<>();
    List<ImageIcon> icons = new ArrayList<>();
    List<JPanel> dishes = new ArrayList<>();
    JPanel menuPanel = new JPanel(new GridLayout(2, 2));
    JPanel functionbar = new JPanel(new FlowLayout());

    private JPanel dish1;
    private JPanel dish2;
    private JPanel dish3;
    private JPanel dish4;


    // EFFECTS: Initial the Menu Panel
    public MenuUI(Menu menu) {
        super(new GridLayout(3, 3));
        this.menu = menu;
        displayDish();


    }

    // MODIFIES: this
    // EFFECTS: Diplay the dish in mean with accoring image & name
    private void displayDish() {
        setupImageIcon();
        setButtonsAndLabels();
        initDish();
        setDishPanel();
    }

    // MODIFIES: this
    // EFFECTS: set up DishPanel, add it to this
    private void setDishPanel() {
        JPanel current;
        for (int i = 0; i < buttons.size(); i++) {
            current = dishes.get(i);
            current.add(buttons.get(i), BorderLayout.CENTER);
            current.add(labels.get(i), BorderLayout.SOUTH);
            add(current);
        }
    }

    // MODIFIES: this
    // EFFECTS: Initial Dish button
    private void initDish() {
        for (int i = 0; i < 12; i++) {
            dishes.add(new JPanel(new BorderLayout()));
        }
    }

    // MODIFIES: this
    // EFFECTS: Set up all button and labels with according
    private void setButtonsAndLabels() {
        int key = 1;
        for (ImageIcon icon : icons) {
            String dishName = menu.getDish(key).getName();
            buttons.add(new DishButton(key, icon));
            JLabel jl = new JLabel("No." + key + ": " + dishName);
            jl.setFont(new Font("Serif", Font.ITALIC, 16));
            labels.add(jl);
            key++;
        }

    }


    // MODIFIES: this
    // EFFECTS: Set up panel for every dish
    private void setDish(int key, JPanel dishPanel) {
        Dish currentDish = menu.getDish(key);
        dishPanel.add(new DishButton(key, icons.get(key)));


    }

    // MODIFIES: this
    // EFFECTS: Loading Image Icons from data.
    private void setupImageIcon() {
        try {
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish1.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish2.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish3.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish4.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish5.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish6.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish7.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish8.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish9.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish10.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish11.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish12.jpg"))));
        } catch (Exception ignored) {
            new Error("Not throwing ");
        }
    }


    // EFFECTS: return the buttons
    public List<DishButton> accessButton() {
        return buttons;
    }

}
