package App;

import MyUtils.MyDataBase;
import Service.*;

public class Main {

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
        UserService.showAllPeople();
        OrderService.getInstance().addEntry(
                UserService.getInstance().getEntry(0),
                DriverService.getInstance().getEntry(0),
                new Food[] {LunchService.getInstance().getEntry(0), DrinkService.getInstance().getEntry(0)},
                "Poporului 10");
        OrderService.getInstance().addEntry(
                UserService.getInstance().getEntry(0),
                DriverService.getInstance().getEntry(0),
                new Food[] {DinnerService.getInstance().getEntry(0), BreakfastService.getInstance().getEntry(0)},
                "Poporului 11");
        OrderService.getInstance().addEntry(
                UserService.getInstance().getEntry(0),
                DriverService.getInstance().getEntry(0),
                new Food[] {DinnerService.getInstance().getEntry(0), BreakfastService.getInstance().getEntry(1)},
                "Poporului 12");
        OrderService.getInstance().printOrders();
        OrderService.getInstance().printMostExpensiveOrder();
//        BreakfastService.getInstance().newEntry();
        MyDataBase.getInstance().loadDataInCsv();
    }
}
