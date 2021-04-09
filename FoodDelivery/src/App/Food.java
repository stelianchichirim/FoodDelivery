package App;

import java.util.ArrayList;

public abstract class Food {

    protected String name;
    protected int price;

    public Food(String _name, int _price) {
        name = _name;
        price = _price;
    }

    // Print all the information about the Food
    public abstract void printInfo();

    // Put all the fields in a list
    public abstract String[] parseToList();

    // Edit the fields of the Food
    public abstract void edit();

    // Get the Food type
    public abstract String getType();

}
