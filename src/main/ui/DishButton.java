package ui;

import javax.swing.*;

public class DishButton extends JButton {
    public final int key;

    public DishButton(int key,ImageIcon icon) {
        super(icon);
        this.key = key;
    }
}
