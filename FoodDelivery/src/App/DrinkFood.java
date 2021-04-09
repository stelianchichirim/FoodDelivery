package App;

import MyUtils.MyIO;

import java.util.ArrayList;

public class DrinkFood extends Food {

    private int capacity;

    public DrinkFood(String _name, int _price, int _capacity) {
        super(_name, _price);
        capacity = _capacity;
    }

    @Override
    public void printInfo() {
        System.out.println(name + " " + capacity + "ml");
        System.out.println(price + " lei");
        MyIO.separator();
    }

    @Override
    public String[] parseToList() {
        return new String[] {name, Integer.toString(price), Integer.toString(capacity)};
    }

    @Override
    public void edit() {

    }

    @Override
    public String getType() {
        return null;
    }
}
