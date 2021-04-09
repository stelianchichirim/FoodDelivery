package App;


import MyUtils.MyIO;

public class Order implements Comparable<Order> {

    private UserPerson user;        // user-ul care a plasat comanda
    private DriverPerson driver;    // persoana care livreaza comanda
    private Food[] orderContent;    // continutul comenzii
    private String address;         // adresa unde trebuie livrata comanda
    private double price;              // pretul comenzii

    public Order(UserPerson _user, DriverPerson _driver, Food[] _orderContent, String _address) {
        user = _user;
        driver = _driver;
        orderContent = _orderContent;
        address = _address;
        price = calculatePrice();
        user.increaseOrders();
    }

    private double calculatePrice() {
        double orderPrice = 0;
        for (Food content : orderContent) orderPrice += content.price;
        orderPrice = orderPrice - ((double)user.getDiscountProcent() / 100.0) * orderPrice;
        return orderPrice;
    }

    public void printInfo() {
        System.out.println("Order made by: " + user.firstName + " " + user.lastName);
        System.out.println("Order delivered by: " + driver.firstName + " " + driver.lastName);
        System.out.println("Address of delivery: " + address);
        System.out.println("Discount applied: " + user.getDiscountProcent() + "%");
        System.out.println("Final price of the order: " + price + " lei");
        System.out.println("Content of the order: ");
        for (Food content : orderContent) content.printInfo();
        MyIO.separator();
    }

    @Override
    public int compareTo(Order ob) {
        return Double.compare(this.price, ob.price);
    }

    public UserPerson getUser() {
        return user;
    }

    public void setUser(UserPerson user) {
        this.user = user;
    }

    public DriverPerson getDriver() {
        return driver;
    }

    public void setDriver(DriverPerson driver) {
        this.driver = driver;
    }

    public Food[] getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(Food[] orderContent) {
        this.orderContent = orderContent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
