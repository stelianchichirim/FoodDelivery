package App;

import MyUtils.MyIO;

import java.util.ArrayList;
import java.util.List;

public class UserPerson extends Person{

    private int discountProcent;
    private int nrOrders;

    public UserPerson(String _firstName, String _lastName, int _discountProcent, int _nrOrders) {
        super(_firstName, _lastName);
        discountProcent = _discountProcent;
        nrOrders = _nrOrders;
    }

    public UserPerson(String _firstName, String _lastName, int _discountProcent) {
        super(_firstName, _lastName);
        discountProcent = _discountProcent;
        nrOrders = 0;
    }

    @Override
    public void printInfo() {
        System.out.println(firstName + " " + lastName);
        System.out.println("Discount: " + discountProcent + "%");
        System.out.println("Total number of orders: " + nrOrders);
        MyIO.separator();
    }

    @Override
    public String[] parseToList() {
        return new String[] {firstName, lastName, Integer.toString(discountProcent), Integer.toString(nrOrders)};
    }

    @Override
    public void edit() {

    }

    @Override
    public String getType() {
        return "UserPerson";
    }

    public int getDiscountProcent() {
        return discountProcent;
    }

    public void increaseOrders() {
        nrOrders++;
    }
}
