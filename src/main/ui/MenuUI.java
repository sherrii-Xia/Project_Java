package ui;

import model.Dish;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuUI extends JPanel {
    Menu menu;
    List<DishButton> buttons = new ArrayList<>();
    List<JLabel> labels = new ArrayList<>();
    List<ImageIcon> icons = new ArrayList<>();
    List<JPanel> dishes = new ArrayList<>();
    JPanel menuPanel = new JPanel(new GridLayout(2, 2));
    JPanel functionbar = new JPanel(new FlowLayout());

    private JPanel dish1;
    private JPanel dish2;
    private JPanel dish3;
    private JPanel dish4;

    public MenuUI(Menu menu) {
        super(new GridLayout(2, 2));
        this.menu = menu;
        displayDish();


    }

    private void displayDish() {
        setupImageIcon();
        setButtonsAndLabels();
        initDish();
        setDishPnal();
    }

    private void setDishPnal() {
        JPanel current;
        for (int i = 0; i < buttons.size(); i++) {
            current = dishes.get(i);
            current.add(buttons.get(i), BorderLayout.CENTER);
            current.add(labels.get(i), BorderLayout.SOUTH);
            add(current);
        }
    }

    private void initDish() {
        for (int i = 0; i < 12; i++) {
            dishes.add(new JPanel(new BorderLayout()));
        }
    }

    //TODO: SET ALL IMAGE BUTTON WITH KEY
    private void setButtonsAndLabels() {
        int key = 1;
        for (ImageIcon icon : icons) {
            String dishName = menu.getDish(key).getName();
            buttons.add(new DishButton(key, icon));
            labels.add(new JLabel("No." + key + ": " + dishName));
            key++;
        }

    }
    //TODO: SET PANEL FOR EVERY DISH

    private void setDish(int key, JPanel dishPanel) {
        Dish currentDish = menu.getDish(key);
        dishPanel.add(new DishButton(key, icons.get(key)));


    }

    private void setupImageIcon() {
        try {
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish1.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish2.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish3.jpg"))));
            icons.add(new ImageIcon(ImageIO.read(new File("./data/dish4.jpg"))));
        } catch (Exception ignored) {
            new Error("Not throwing ");
        }
    }

}
