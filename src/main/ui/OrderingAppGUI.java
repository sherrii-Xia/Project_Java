package ui;

import model.Dish;
import model.Order;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderingAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/Order.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Menu menu;
    private Order order;

    private Scanner input;
    private String customer;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JPanel panel;
    private JButton loginButton;


    java.util.List<JButton> buttons = new ArrayList<>();
    List<ImageIcon> icons;
    JPanel menuPanel;
    JPanel functionbar = new JPanel(new FlowLayout());
    private JFrame menuframe;
    private JButton addButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton displayButton;
    private JButton quitButton;
    private JButton finalQuit = new JButton("quit");

//    private J
    //TODO: DISPLAY THE MENUS, each prictur with the select bottnm
    //Select bottom  will give the decription + add/remove
    //MENU

    public OrderingAppGUI() {
        super("The Ordering App");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menu = new Menu();
        menu.settingUpDish();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(400, 300);
        setVisible(true);
        panel = new JPanel();
        add(panel);
        setButton(); //TODO : THE LOGIN of the order
        setOrder(); //TODO: THE BUTTON OF ORDER +


    }

    private void setOrder() {
    }


    private void setButton() {
        BufferedImage buttonIcon;
        try {
            buttonIcon = ImageIO.read(new File("./data/hotpot.jpg"));
            loginButton = new JButton("Create an order for Hotpot Hotpot !", new ImageIcon(buttonIcon));
        } catch (Exception ignored) {
            // pass
        }

//        JLabel jl = new JLabel("Create an order for Hotpot Hotpot !");
        loginButton.setVerticalTextPosition(JButton.BOTTOM);
        loginButton.setHorizontalTextPosition(JButton.CENTER);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setEnabled(true);
        login();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        panel.add(loginButton);
//        panel.add(jl);
        pack();

    }

    private void login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = JOptionPane.showInputDialog("Enter the username to create an new order");
                order = new Order(userName);
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Hello " + userName + "!\n" + "Welcome to HOTPOT HOTPOT!");
                setVisible(false);
                setUpMenuFrame();

            }
        });
    }


    //TODO: Menu frame.
    private void setUpMenuFrame() {
        menuframe = new JFrame("Menu of the Restaurant");
        menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuframe.setSize(800, 600);
        menuframe.setLocation(400, 300);
        menuframe.setVisible(true);
        menuframe.setLayout(new BorderLayout());
        displayDish();
        setupFunctionBar();
    }

    private void displayDish() {
        menuPanel = new MenuUI(menu);
        menuframe.add(menuPanel, BorderLayout.CENTER);


    }

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
        menuframe.add(functionbar, BorderLayout.SOUTH);


    }

    private void quit() {
        quitButton.addActionListener(new ActionListener() {
            JFrame orderView = new JFrame();
            JTextArea orderText = new JTextArea();

            @Override
            public void actionPerformed(ActionEvent e) {
                displayOrder(orderText);
                orderView.setVisible(true);
                orderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                orderView.setSize(400, 300);
                orderView.setLocation(400, 300);
                orderView.add(orderText);
                orderView.add(finalQuit);
                menuframe.setVisible(false);
                realquit();
//                System.exit(0);
            }
        });
    }

    private void realquit() {
        finalQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    private void loadBill() {

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    order = jsonReader.read();
                    JOptionPane.showMessageDialog(null, "Loaded " + order.getName() + "'s order from " + JSON_STORE);
                } catch (IOException fe) {
                    JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }

    private void saveBill() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(order);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null,
                            "Saved " + order.getName() + "'s order to " + JSON_STORE);
                } catch (FileNotFoundException fe) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    //TODO: 一个弹窗 print 菜单
    private void viewBill() {

        viewButton.addActionListener(new ActionListener() {
            JFrame orderView = new JFrame("Current Order");
            JTextArea orderText = new JTextArea();

            @Override
            public void actionPerformed(ActionEvent e) {
                displayOrder(orderText);
                orderView.setVisible(true);
                orderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                orderView.setSize(400, 300);
                orderView.setLocation(400, 300);
                orderView.add(orderText);


            }
        });


    }

    private void displayOrder(JTextArea orderText) {

        orderText.append("The current summary of the Order");
        orderText.append("\nDish                               Cost");
        for (Dish current : order.getDishes()) {
            orderText.append("\n " + current.getName());
            orderText.append("\n" + current.getPrice() * current.getCount());

            if (current.getCount() > 1) {
                orderText.append("\n\t$" + current.getCount() + " @ $" + current.getPrice() + " each");
            }
        }
//
        orderText.append("\nThe total Amount : " + "$" + order.getTotalAmount());
    }


}
