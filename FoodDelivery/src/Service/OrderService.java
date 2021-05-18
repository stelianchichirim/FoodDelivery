package Service;

import App.DriverPerson;
import App.Food;
import App.Order;
import App.UserPerson;
import MyUtils.MyIO;
import MyUtils.MyLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class OrderService {

    // We keep the orders increasingly by their price
    private TreeSet<Order> orders;

    private static OrderService instance = null;

    private OrderService() {
        orders = new TreeSet<>();
    }

    public static OrderService getInstance() {
        if (instance == null) instance = new OrderService();
        return instance;
    }

    public void addEntry(UserPerson user, DriverPerson driver, List<Food> orderContent, String address) {
        orders.add(new Order(user, driver, orderContent, address));
    }

    public void newEntry() {
        try {
            List<Food> orderContent = new ArrayList<>();
            for (FoodService classService : FoodService.getServiceInstances()) {
                System.out.println("Write the id's separated by commas of the " + classService.tableName() + " included in the order (write -1 for nothing):\n");
                String[] ids = MyIO.getInstance().readLine().split(",");
                if (Integer.parseInt(ids[0]) == -1) continue;
                for (String id : ids) {
                    Food orderFood = classService.getEntry(Integer.parseInt(id));
                    if (orderFood != null) orderContent.add(orderFood);
                    else throw new Exception("Id not found!");
                }
            }

            System.out.println("Write the id of the user who placed the command:\n");
            String idUser = MyIO.getInstance().readLine();
            UserPerson user = UserService.getInstance().getEntry(Integer.parseInt(idUser));
            if (user == null) throw new Exception("Id not found!");

            System.out.println("Write the address where the command should be delivered:\n");
            String address = MyIO.getInstance().readLine();

            System.out.println("Write the id of the driver who should deliver the command:\n");
            String idDriver = MyIO.getInstance().readLine();
            DriverPerson driver = DriverService.getInstance().getEntry(Integer.parseInt(idDriver));
            if (driver == null) throw new Exception("Id not found!");

            addEntry(user, driver, orderContent, address);

            MyLogger.getInstance().addLog("Create new Order entry");
            MyIO.succesCommand();

        } catch (Exception e) {
            System.out.println("Given Id does not exist!\n");
        }
    }

    public void printOrders() {
        for (Order order : orders.descendingSet())
            if (order != null) order.printInfo();
        MyIO.separator();

        MyLogger.getInstance().addLog("Show Order entries");
    }

    public void printMostExpensiveOrder() {
        if (!orders.isEmpty()) orders.last().printInfo();

        MyLogger.getInstance().addLog("Show Most Expensive Order entry");
    }

    public TreeSet<Order> getOrders() {
        return orders;
    }

    public void setOrders(TreeSet<Order> orders) {
        this.orders = orders;
    }
}
