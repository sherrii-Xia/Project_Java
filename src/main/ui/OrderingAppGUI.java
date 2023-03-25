package ui;

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
    private JPanel menuPanel;
    private JButton loginButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton displayButton;
    private JButton quitButton;

//    private J
    //TODO: DISPLAY THE MENUS, each prictur with the select bottnm
    //Select bottom  will give the decription + add/remove
    //MENU

    public OrderingAppGUI() {
        super("The Ordering App");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(400, 300);
        setVisible(true);
        panel = new JPanel();
        add(panel);
        setButton(); //TODO : THE LOGIN of the order
        setMenuImage(); //TODO: THE DISPLAY
        setOrder(); //TODO: THE BUTTON OF ORDER +





    }

    private void setOrder() {
    }

    //TODO: THE IMAGE OF MENU WILL BE IN THE MENU UI
    private void setMenuImage() {
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
                menu = new Menu();
                setUpMenuFrame();

            }
        });
    }


    //TODO: Menu frame.
    private void setUpMenuFrame() {
        new MenuUI(menu);
    }
}
