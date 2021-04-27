package Service;

import App.DriverPerson;
import App.Food;
import App.Order;
import App.UserPerson;
import MyUtils.MyIO;
import MyUtils.MyLogger;

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

    public void addEntry(UserPerson user, DriverPerson driver, Food[] orderContent, String address) {
        orders.add(new Order(user, driver, orderContent, address));
    }

    public void newEntry() { }

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
