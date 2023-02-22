package ui;

import model.Dish;
import model.Order;

import java.util.Scanner;

public class OrderingApp {
    //TODO: where the ordering systems interactive happens

    //TODO: show meuns => options : choose dish , view order, check bill, and quit
    //TODO: choose dish => add to oder (show the decription && the falvor => spicy or not), see description,(alger alter
    //TODO: view order => all the dish in the order with the cout
    //                 => the totoal amount
    //                 => remove the order
    //
    private Menu menu;
    private Order order;

    private Scanner input;

    public OrderingApp() {
        this.runOrder();
    }

    public void runOrder() {
        setup();
        boolean state = true;
        String choice = null;
        while (state) {
            showOption();
            System.out.println("\nEnter your choice : ");
            choice = input.next();
            state = makeChoice(choice);


        }

    }

    public void setup() {
        menu = new Menu();
        order = new Order("Amy");
        menu.displayMenu();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //TODO: show all the option
    public void showOption() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add dish to order ");
        System.out.println("\tr -> remove the dish to order");
        System.out.println("\tv -> view order & bill");
        System.out.println("\tm -> view the full menu again");
        System.out.println("\tq -> check bill & quit");
    }

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
        } else {
            System.out.println("Please enter the correct letter");
        }
        return true;

    }

    public void doAddDish() {
        System.out.println("Enter the Number Key of Dish You want to Add: ");
        String index0 = input.next();
        int index = 0;
        try {
            index = Integer.valueOf(index0);
            Dish newDish = menu.getDish(index);
            order.addDish(newDish);
        } catch (NumberFormatException ex) {
            System.out.println("Please input the key of the Dish as a Integer!");
        }


    }

    public void doRemoveDish() {
        System.out.println("Enter the Number Key of Dish you want to remove: ");
        String index0 = input.next();
        int index = 0;
        try {
            index = Integer.valueOf(index0);
            Dish newDish = menu.getDish(index);
            boolean success = order.removeDish(newDish);
            if (!success) {
                System.out.println("Can Not Remove a Dish does not exist in the Order");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Please input the key of the Dish as a Integer!");
        }

    }

    public void checkBill() {
        System.out.println("Checking bill ...");
        System.out.println("--------------------------------");
        order.displayOrder();
        System.out.println("--------------------------------");
        System.out.println("The bill has been checked!");

    }

    public void viewMenu() {
        menu.displayMenu();
    }

    public void viewOrder() {
        order.displayOrder();

    }


}
