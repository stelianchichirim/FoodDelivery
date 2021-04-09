package Service;

import App.DriverPerson;
import App.Food;
import App.Order;
import App.UserPerson;
import MyUtils.MyIO;

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

    public void printOrders() {
        for (Order order : orders.descendingSet())
            order.printInfo();
        MyIO.separator();
    }

    public void printMostExpensiveOrder() {
        if (!orders.isEmpty()) orders.last().printInfo();
    }

}
