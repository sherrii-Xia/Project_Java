package ui;

import model.Dish;
import model.Order;

import java.util.Scanner;

public class OrderingApp {
    private Menu menu;
    private Order order;

    private Scanner input;
    private String customer;



    //EFFECT: start the ordering app
    public OrderingApp() {
        this.runOrder();
    }


    //MODIFIES: this
    //EFFECT: run the ordering application and processes user input
    public void runOrder() {
        setup();
        boolean state = true;
        String choice;
        menu.displayMenu();
        while (state) {
            showOption();
            System.out.println("\nEnter your choice : ");
            choice = input.next();
            state = makeChoice(choice);


        }

    }


    //MODIFIES: this
    //EFFECT: initializes the menus and order.
    public void setup() {
        menu = new Menu();
        order = new Order(customer);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Enter your name to start ordering : ");
        customer = input.next();
        System.out.println("\nHello, " + customer + "!");
        System.out.println("Welcome to Hotpot Hotpot!");
    }


    //EFFECT: display the options and associated command.
    public void showOption() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add dish to order ");
        System.out.println("\tr -> remove the dish to order");
        System.out.println("\tredo -> redo the order");
        System.out.println("\tv -> view order & bill");
        System.out.println("\tm -> view the full menu again");
        System.out.println("\tq -> check bill & quit");
    }


    //MODIFIES: this
    //EFFECT: process the inputted choice (command).
    public boolean makeChoice(String c) {
        if (c.equals("q")) {
            checkBill();
            System.out.println("Thank you for ordering! order has been processed!");
            return false;
        } else if (c.equals("a")) {
            doAddDish();

        } else if (c.equals("r")) {
            doRemoveDish();

        } else if (c.equals("v")) {
            viewOrder();
        } else if (c.equals("m")) {
            viewMenu();
        } else if (c.equals("redo")) {
            cleanOrder();
        } else {
            System.out.println("Please enter the correct letter");
        }
        return true;

    }

    //MODIFIES: this
    //EFFECT: clean up all dishes been added to the order
    public void cleanOrder() {
        order = new Order(customer);
    }


    //MODIFIES: this
    //EFFECT: adding the selected dish to order
    public void doAddDish() {
        System.out.println("Enter the Number Key of Dish You want to Add: ");
        String index0 = input.next();
        int index;
        try {
            index = Integer.valueOf(index0);
            Dish newDish = menu.getDish(index);
            order.addDish(newDish);
            System.out.println("Dish is added successfully !");
        } catch (NumberFormatException ex) {
            System.out.println("Please input the key of the Dish as a Integer!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Dish doest not exists in menus !");
        }


    }


    //MODIFIES: this
    //EFFECT: removing the selected dishh if the dish is in the order.
    public void doRemoveDish() {
        System.out.println("Enter the Number Key of Dish you want to remove: ");
        String index0 = input.next();
        int index;
        try {
            index = Integer.valueOf(index0);
            Dish newDish = menu.getDish(index);
            boolean success = order.removeDish(newDish);
            if (!success) {
                System.out.println("Can Not Remove a Dish does not exist in the Order");
            } else {
                System.out.println("Dish is removed successfully !");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Please input the key of the Dish as a Integer!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Dish doest not exists in menus !");
        }


    }

    //MODIFIES: this
    //EFFECT: display the bill.
    public void checkBill() {
        System.out.println("Checking bill ...");
        System.out.println("------------------------------------");
        displayOrder(order);
        System.out.println("------------------------------------");
        System.out.println("The bill has been checked!");
        System.out.println("Have a nice day !");

    }

    //MODIFIES: this
    //EFFECT: display the menu again.
    public void viewMenu() {
        menu.displayMenu();
    }

    //MODIFIES: this
    //EFFECT: display current order.
    public void viewOrder() {
        displayOrder(order);

    }

    //MODIFIES: this
    //EFFECT: display the given order .
    public void displayOrder(Order order) {
        System.out.println("The current summary of the Order");
        System.out.printf("%-35s%-10s\n", "Dish", "Cost");
        for (Dish current : order.getDishes()) {
            System.out.printf("%-35s%-5.2f\n", current.getName(), current.getPrice() * current.getCount());
            if (current.getCount() > 1) {
                System.out.println("\t" + " $" + current.getCount() + " @ $" + current.getPrice() + " each");
            }
        }

        System.out.println("\nThe total Amount : " + "$" + order.getTotalAmount());
    }


}
