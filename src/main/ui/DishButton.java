package ui;

import javax.swing.*;
/**
 * Represents a Button with the key of Dish stands for
 */

public class DishButton extends JButton {
    public final int key;

    //EFFECTS: JButton with unique key (Dish Key)
    public DishButton(int key,ImageIcon icon) {
        super(icon);
        this.key = key;
    }
}
