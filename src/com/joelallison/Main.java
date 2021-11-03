package com.joelallison;

import java.util.*;

public class Main {

    static void printNiceList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i == list.size() - 1) {
                System.out.println(".");
            } else {
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<String> availableToppings = new ArrayList<>(Arrays.asList(
                "pepperoni", "chicken", "sweetcorn", "ham", "mushrooms",
                "onions", "pineapple", "bacon", "green and red peppers",
                "jalapeno peppers", "olives", "ground beef", "tomatoes", "sliced gherkins"));
        ArrayList<String> toppingsOnPizza = new ArrayList<>(Arrays.asList("tomato sauce", "mozzarella"));

        HashMap<String, Double> prices = new HashMap<String, Double>() {{
            put("small", 7.99);
            put("medium", 10.99);
            put("large", 13.99);
        }};

        boolean ordered = false;
        boolean sizeChosen = false;
        boolean toppingsDisplayed = false;
        String tempInput;
        String size = "";

        while (!ordered) {
            while (!sizeChosen) {
                System.out.println("What size pizza would you like?\nA small is £7.99, a medium is £10.99, a large is £13.99\n(All pizzas come with tomato sauce and mozzarella cheese)\n");
                size = input.next();
                if (size.equalsIgnoreCase("small") || size.equalsIgnoreCase("medium") || size.equalsIgnoreCase("large")) {
                    sizeChosen = true;
                } else {
                    System.out.println("That's not a valid size... please enter 'small', 'medium' or 'large.");
                }
            }

            if (!toppingsDisplayed) {
                System.out.print("\nThese are the available toppings: ");
                printNiceList(availableToppings);
            }
            toppingsDisplayed = true;

            System.out.print("\nCurrently your pizza has:");
            printNiceList(toppingsOnPizza);

            System.out.println("Would you like to add more toppings? [yes/no]");
            tempInput = input.next();
            if (tempInput.equalsIgnoreCase("yes") || tempInput.equalsIgnoreCase("y")) {
                System.out.println("Please enter the name of the topping you want to add from the list shown above, or if you want to change the size of the pizza type 's', and if you want to see the list of toppings again type 't':");
                tempInput = input.next();
                if (tempInput.equalsIgnoreCase("s")) {
                    sizeChosen = false;
                } else if (tempInput.equalsIgnoreCase("t")) {
                    toppingsDisplayed = false;
                } else if (!availableToppings.contains(tempInput.toLowerCase())) {
                    System.out.println("That's not a topping!");
                } else {
                    toppingsOnPizza.add(tempInput);
                }
            } else {
                ordered = true;
            }
        }
        System.out.print("\nYou ordered a " + size + " pizza, with ");
        printNiceList(toppingsOnPizza);
        System.out.println("It costs £" + (prices.get(size) + Math.max(0, (toppingsOnPizza.size() - 4)) * 1.25));
    }
}