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

/**
 * Represent GUI of Ordering App
 */

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
    MenuUI menuPanel;
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
    private JButton redoButton;


    //TODO: DISPLAY THE MENUS, each prictur with the select bottnm
    //Select bottom  will give the decription + add/remove
    //MENU

    //EFFECTS: Set up Ordering App GUI
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
        setButton();


    }

    // MODIFIES: this
    // EFFECTS: SetUp Buttons for User login
    private void setButton() {
        BufferedImage buttonIcon;
        try {
            buttonIcon = ImageIO.read(new File("./data/hotpot.jpg"));
            loginButton = new JButton("Create an order for Hotpot Hotpot !", new ImageIcon(buttonIcon));
            loginButton.setFont(new Font("Serif", Font.ITALIC, 24));
        } catch (Exception ignored) {
            // pass
        }

        loginButton.setVerticalTextPosition(JButton.BOTTOM);
        loginButton.setHorizontalTextPosition(JButton.CENTER);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setEnabled(true);
        login();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        panel.add(loginButton);
        pack();

    }

    // MODIFIES: this
    // EFFECTS:When the Login button is clicked, show dialog and let user input their name.
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


    // MODIFIES: this
    // EFFECTS:Setting up the menu Frame
    private void setUpMenuFrame() {
        menuframe = new JFrame("Menu of the Restaurant");
        menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuframe.setSize(1000, 800);
        menuframe.setLocation(300, 300);
        menuframe.setVisible(true);
        menuframe.setLayout(new BorderLayout());
        displayDish();
        setupFunctionBar();
    }

    // MODIFIES: this
    // EFFECTS:Display the dish in the menu with icon
    private void displayDish() {
        menuPanel = new MenuUI(menu);
        menuframe.add(menuPanel, BorderLayout.CENTER);
        addAndRemove();


    }

    // MODIFIES: this
    // EFFECTS:Display the dish in the menu with icon
    // Method refer to :https://www.youtube.com/watch?v=OI-TFbHQhtA
    private void addAndRemove() {
        ActionListener addAndRemove = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DishButton o = (DishButton) e.getSource();
                Dish dish = menu.getDish(o.key);
                JTextArea inreg = renderIngret(dish);
                JFrame dishframe = new JFrame("Choose the Option");
                addButton = new JButton("add the dish");
                removeButton = new JButton("remove the dish");
                dishframe.setVisible(true);
                dishframe.setLocation(300, 300);
                dishframe.setSize(200, 200);
                dishframe.setLayout(new FlowLayout());
                dishframe.add(inreg);
                dishframe.add(addButton);
                addDish(o, dishframe);
                dishframe.add(removeButton);
                removeDish(o, dishframe);

            }
        };

        for (DishButton button : menuPanel.accessButton()) {
            button.addActionListener(addAndRemove);

        }

    }

    // MODIFES: this
    //EFFECT: DISPLAY Ingrrdients in Dish Frame
    //REFER TO: https://stackoverflow.com/questions/4019981/auto-end-line-in-jtextarea
    private JTextArea renderIngret(Dish dish) {
        JTextArea ingret = new JTextArea("The ingredient :\n" + dish.getIngredients());
        ingret.setSize(200,100);
        ingret.setFont(new Font("Serif", Font.ITALIC, 16));
        ingret.setLineWrap(true);
        ingret.setWrapStyleWord(true);
        ingret.setEditable(false);
        return  ingret;
    }

    // MODIFIES: this
    // EFFECTS:  remove the dish when remove Button is clicked
    private void removeDish(DishButton button, JFrame frame) {
        Dish d = menu.getDish(button.key);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean success = order.removeDish(d);
                if (success) {
                    JOptionPane.showMessageDialog(OrderingAppGUI.this,
                            "Dish is removed successfully !");
                } else {
                    JOptionPane.showMessageDialog(OrderingAppGUI.this,
                            "ERROR: Can not remove a dish not exits in order");
                }
                frame.setVisible(false);


            }
        });

    }

    // MODIFIES: this
    // EFFECTS:  add Dish to the order when add button is clicked.
    private void addDish(DishButton button, JFrame frame) {
        Dish d = menu.getDish(button.key);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.addDish(d);
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Dish is added successfully !");
                frame.setVisible(false);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS:  Setting up the function bar with viewButton, saveButton, loadButton,and quitBUtton.
    private void setupFunctionBar() {
        viewButton = new JButton("view order & bill");
        viewBill();
        saveButton = new JButton("Save the current order to file");
        saveBill();
        loadButton = new JButton("load previous order from file");
        loadBill();
        quitButton = new JButton("check bill & quit");
        quit();
        redoButton = new JButton("redo order");
        redo();
        functionbar.add(viewButton);
        functionbar.add(saveButton);
        functionbar.add(loadButton);
        functionbar.add(redoButton);
        functionbar.add(quitButton);
        menuframe.add(functionbar, BorderLayout.SOUTH);


    }


    // MODIFIES: this
    // EFFECTS:  redo the order when redoButton is clicked.
    private void redo() {
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order = new Order(order.getName());
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Order is redone successfully !");

            }
        });

    }

    // MODIFIES: this
    // EFFECTS:  quit the Ordering Application when quit button is clicked.
    private void quit() {
        JFrame orderView = new JFrame("Exit Windows");
        orderView.setLayout(new BorderLayout());
        JTextArea orderText = new JTextArea();
        JLabel topBar = new JLabel("Thanks for visiting Hotpot Hotpot!");
        JPanel downBar = new JPanel(new FlowLayout());
        quitButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                displayOrder(orderText, "The final summary of the Order");
                orderView.setVisible(true);
                orderView.setSize(400, 300);
                orderView.setLocation(400, 300);
                orderView.add(orderText, BorderLayout.CENTER);
                downBar.add(finalQuit);
                downBar.add(saveButton);
                orderView.add(topBar, BorderLayout.NORTH);
                orderView.add(downBar, BorderLayout.SOUTH);
                menuframe.setVisible(false);
                realquit();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS:  terminate the program when finalQuit button is clicked.
    private void realquit() {
        finalQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }


    // MODIFIES: this
    // EFFECTS:  loading the previous bill when loadBution is clicked.
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


    // MODIFIES: this
    // EFFECTS:  save Bill when the SaveBill is clicked.
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

    // MODIFIES: this
    // EFFECTS:  view the current order with total cost when viewButton is clicked.
    private void viewBill() {
        JFrame orderView = new JFrame("Current Order");
        JTextArea orderText = new JTextArea();
        orderText.setFont(new Font("Serif", Font.ITALIC, 15));

        viewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                orderText.setText("");
                displayOrder(orderText, "The current summary of the Order");
                orderText.setEditable(false);
                orderView.setVisible(true);
                orderView.setSize(400, 400);
                orderView.setLocation(400, 300);
                orderView.add(orderText);


            }
        });


    }


    // MODIFIES: orderText
    // EFFECTS: Display the order in orderText.
    private void displayOrder(JTextArea orderText, String title) {
        orderText.setEditable(false);
        orderText.append(title);
        orderText.append("\nDish                               \t\tCost");
        for (Dish current : order.getDishes()) {
            orderText.append("\n " + current.getName());
            orderText.append("\n\t\t\t$" + current.getPrice() * current.getCount());

            if (current.getCount() > 1) {
                orderText.append("\n\t$" + current.getCount() + " @ $" + current.getPrice() + " each");
            }
        }

        orderText.append("\nThe total Amount : " + "$" + order.getTotalAmount());
    }


}
