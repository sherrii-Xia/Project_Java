package ui;

import model.Dish;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class MenuUI extends JFrame {
    Menu menu;
    JButton dish1;
    JButton dish2;
    JButton dish3;
    JButton dish4;
    JButton dish5;
    JButton dish6;
    JButton dish7;
    JButton dish8;
    JButton dish9;
    JButton dish10;
    List<BufferedImage> icons;

    public MenuUI(Menu menu) {
        super("Menu of the Restaurant");
        this.menu = menu;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(400, 300);
        setVisible(true);
        setLayout(new GridLayout(0, 2));
    }

    private void displayDish() {
        setupImageIcon();
        int key = 0;
        for (Dish d : menu.getTotalDish()) {
            JLabel jl = new JLabel("NO." + key + " " + d.getName() + " $" + d.getPrice());

//            try {
//                buttonIcon = ImageIO.read(new File("./data/hotpot.jpg"));
//                loginButton = new JButton("Create an order for Hotpot Hotpot !", new ImageIcon(buttonIcon));
//            } catch (Exception ignored) {
//                // pass
//            }
//
////        JLabel jl = new JLabel("Create an order for Hotpot Hotpot !");
//            loginButton.setVerticalTextPosition(JButton.BOTTOM);
//            loginButton.setHorizontalTextPosition(JButton.CENTER);
//            loginButton.setBorder(BorderFactory.createEmptyBorder());
//            loginButton.setEnabled(true);
            key++;
        }

    }

    private void setupImageIcon() {
        try {
            icons.add(ImageIO.read(new File("image name and path")));
        } catch (Exception ignored) {
            //should pass
        }
    }

}
