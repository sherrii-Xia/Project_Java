package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuUI extends JFrame {
    Menu menu;
    List<JButton> buttons = new ArrayList<>();
    List<ImageIcon> icons;
    JPanel menuPanel = new JPanel(new GridLayout(2, 2));
    JPanel functionbar = new JPanel(new FlowLayout());
    private JButton addButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton displayButton;
    private JButton quitButton;

    public MenuUI(Menu menu) {
        super("Menu of the Restaurant");
        this.menu = menu;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(400, 300);
        setVisible(true);
        setLayout(new BorderLayout());
        add(new JButton("di1"));
        displayDish();
        setupFunctionBar();
    }

    //     System.out.println("\tc -> choose the dish to add or remove");
//        System.out.println("\tredo -> redo the order");
//        System.out.println("\tv -> view order & bill");
//        System.out.println("\tm -> view the full menu again");
//        System.out.println("\ts -> save current order to file");
//        System.out.println("\tl -> load previous order from file");
//        System.out.println("\tq -> check bill & quit");
    private void setupFunctionBar() {
        viewButton = new JButton("view order & bill");
        viewBill();
        saveButton = new JButton("Save the current order to file");
        saveBill();
        loadButton = new JButton("load previous order from file");
        loadBill();
        quitButton = new JButton("check bill & quit");
        quit();

        functionbar.add(viewButton);
        functionbar.add(saveButton);
        functionbar.add(loadButton);
        functionbar.add(quitButton);
        add(functionbar);


    }

    private void quit() {
    }

    private void loadBill() {
    }

    private void saveBill() {
    }

    private void viewBill() {

    }

    private void displayDish() {
        menuPanel.add(new JButton("DIsh 1"));
        menuPanel.add(new JButton("DISH 2 "));
        menuPanel.add(new JButton("DIsh 3"));
        menuPanel.add(new JButton("DISH 4 "));
        menuPanel.add(new JButton("DIsh 5"));
        menuPanel.add(new JButton("DISH 6 "));
        add(menuPanel);

//        setupImageIcon();
//        int key = 0;
//        JButton current;
////        try {
////            current = new JButton("NO." + key, new ImageIcon(ImageIO.read(new File("./data/hotpot.jpg"))));
////            current.setVerticalTextPosition(JButton.BOTTOM);
////            current.setHorizontalTextPosition(JButton.CENTER);
////            current.setBorder(BorderFactory.createEmptyBorder());
////            current.setEnabled(true);
////            panel.add(current);
////        } catch (Exception e) {
////            //pass
////        }
//        for (Dish d : menu.getTotalDish()) {
//            if (key > 4) {
//                break;
//            }
//            current = new JButton("NO." + key + " " + d.getName() + " $" + d.getPrice(), icons.get(key));
//            current.setVerticalTextPosition(JButton.BOTTOM);
//            current.setHorizontalTextPosition(JButton.CENTER);
//            current.setBorder(BorderFactory.createEmptyBorder());
//            current.setEnabled(true);
//            add(current);
//
//            key++;
//        }

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
