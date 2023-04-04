package ui;

import javax.swing.*;
/**
 * Represents a Button with the key of Dish is stands for
 */

public class DishButton extends JButton {
    public final int key;

    //EFFECTS: JButon with unique key (Dish Key)
    public DishButton(int key,ImageIcon icon) {
        super(icon);
        this.key = key;
    }
}
