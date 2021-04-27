package App;

import MyUtils.MyDataBase;
import MyUtils.MyIO;
import MyUtils.MyLogger;
import Service.*;

import java.net.SocketOption;
import java.sql.SQLOutput;

public class Main {

    public static String createCommands = "[Food Breakfast / Food Lunch / Food Dinner / Food Drink / Person User / Person Driver / Order]";
    public static String showCommands = "[Food Breakfast / Food Lunch / Food Dinner / Food Drink / Person User / Person Driver / Order / Logger]";
    public static String editCommands = "[Food Breakfast / Food Lunch / Food Dinner / Food Drink / Person User / Person Driver] [id]";
    public static String deleteCommands = "[Food Breakfast / Food Lunch / Food Dinner / Food Drink / Person User / Person Driver] [id]";


    public static void main(String[] args) {
        MyDataBase.getInstance().loadDataFromCsv();

//        BreakfastService.getInstance().showFood();
//        BreakfastService.getInstance().create(new String[]{"Oua ochiuri", "25", "500", "True", "foarte bune!"});
//        LunchService.getInstance().create(new String[]{"Gratar", "30", "600", "False", "foarte bune!"});
//        DinnerService.getInstance().create(new String[]{"Peste prajit", "35", "600", "False", "foarte bune!"});
//        DrinkService.getInstance().create(new String[]{"Coca-Cola", "7", "500"});
//        UserService.getInstance().create(new String[]{"Mihai", "Popescu", "10", "0"});
//        DriverService.getInstance().create(new String[]{"Alex", "Constantin", "3000"});
//        FoodService.showAllFood();
//        UserService.showAllPeople();

        // for testing, we need to have at least one object of each class to work
        try {
            OrderService.getInstance().addEntry(
                    UserService.getInstance().getEntry(0),
                    DriverService.getInstance().getEntry(0),
                    new Food[]{LunchService.getInstance().getEntry(0), DrinkService.getInstance().getEntry(0)},
                    "Poporului 10");
            OrderService.getInstance().addEntry(
                    UserService.getInstance().getEntry(0),
                    DriverService.getInstance().getEntry(0),
                    new Food[]{DinnerService.getInstance().getEntry(0), BreakfastService.getInstance().getEntry(0)},
                    "Poporului 11");
            OrderService.getInstance().addEntry(
                    UserService.getInstance().getEntry(0),
                    DriverService.getInstance().getEntry(0),
                    new Food[]{DinnerService.getInstance().getEntry(0), DrinkService.getInstance().getEntry(0)},
                    "Poporului 12");
        }
        catch (Exception e) {
            System.out.println("We need to have at least one object of each class to work the order test");
        }

//        OrderService.getInstance().printOrders();
//        OrderService.getInstance().printMostExpensiveOrder();
//        BreakfastService.getInstance().newEntry();

        // CLI menu

        System.out.println("Hello to Food Delivery Application!\n");
        System.out.println("Type 'help' for available commands!\n");

        while (true) {
            String[] commands = MyIO.getInstance().readCommands();

            if (commands[0].equals("exit")) {
                break;
            }

            if (commands[0].equals("help")) {
                System.out.println("\nAvailable commands:\n");
                System.out.println("     create " + createCommands);
                System.out.println("     show   " + showCommands);
                System.out.println("     update " + editCommands);
                System.out.println("     delete " + deleteCommands);
                System.out.println("     exit (close the application and save the new data)\n");
                continue;
            }

            if (commands.length == 1) {
                MyIO.wrongCommand();
                continue;
            }

            FoodService foodInstance = null;
            PersonService personInstance = null;
            OrderService orderInstance = null;
            MyLogger loggerInstance = null;

            if (commands[1].equals("Order")) orderInstance = OrderService.getInstance();
            else if (commands[1].equals("Logger")) loggerInstance = MyLogger.getInstance();
            else if (commands.length > 2 && commands[1].equals("Food")) {
                switch (commands[2]) {
                    case "Breakfast" -> foodInstance = BreakfastService.getInstance();
                    case "Lunch" -> foodInstance = LunchService.getInstance();
                    case "Dinner" -> foodInstance = DinnerService.getInstance();
                    case "Drink" -> foodInstance = DrinkService.getInstance();
                }
            }
            else if (commands.length > 2 && commands[1].equals("Person")) {
                switch (commands[2]) {
                    case "User" -> personInstance = UserService.getInstance();
                    case "Driver" -> personInstance = DriverService.getInstance();
                }
            }

            if (commands[0].equals("create")) {
                if (foodInstance != null) foodInstance.newEntry();
                else if (personInstance != null) personInstance.newEntry();
                //else if (orderInstance != null) orderInstance.newEntry();
                else MyIO.wrongCommand();
                continue;
            }

            if (commands[0].equals("show")) {
                if (foodInstance != null) foodInstance.showFood();
                else if (personInstance != null) personInstance.showPeople();
                else if (loggerInstance != null) loggerInstance.showLogger();
                else if (orderInstance != null) {
                    System.out.println("Select option:");
                    System.out.println("     1. Most expensive order");
                    System.out.println("     2. All orders\n");

                    try {
                        int option = Integer.parseInt(MyIO.getInstance().readLine());
                        if (option == 1) orderInstance.printMostExpensiveOrder();
                        else if (option == 2) orderInstance.printOrders();
                        else MyIO.wrongCommand();
                    }
                    catch (Exception e) {
                        System.out.println("Option must be a number!\n");
                    }
                }
                else MyIO.wrongCommand();
                continue;
            }

            if (commands[0].equals("update")) {
                try {
                    int id = Integer.parseInt(commands[3]);
                    if (foodInstance != null) foodInstance.edit(id);
                    else if (personInstance != null) personInstance.edit(id);
                    else MyIO.wrongCommand();
                }
                catch (Exception e) {
                    MyIO.wrongCommand();
                }
                continue;
            }

            if (commands[0].equals("delete")) {
                try {
                    int id = Integer.parseInt(commands[3]);
                    if (foodInstance != null) foodInstance.delete(id);
                    else if (personInstance != null) personInstance.delete(id);
                    else MyIO.wrongCommand();
                }
                catch (Exception e) {
                    MyIO.wrongCommand();
                }
                continue;
            }

            MyIO.wrongCommand();
        }

        MyDataBase.getInstance().loadDataInCsv();
    }
}
