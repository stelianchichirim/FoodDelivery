package App;

import MyUtils.MyIO;

import java.util.ArrayList;

public class LunchFood extends  Food {

    private int size;
    private boolean vegan;
    private String description;

    public LunchFood(String _name, int _price, int _size, boolean _vegan, String _description) {
        super(_name, _price);
        vegan = _vegan;
        size = _size;
        description = _description;
    }

    @Override
    public void printInfo() {
        if (vegan) System.out.print("\uD83C\uDF31");
        System.out.println(name + " " + size + "g");
        System.out.println(description);
        System.out.println(price + " lei");
        MyIO.separator();
    }

    @Override
    public String[] parseToList() {
        return new String[] {name, Integer.toString(price), Integer.toString(size), String.valueOf(vegan), description};
    }

    @Override
    public void edit() {

    }

    @Override
    public String getType() {
        return null;
    }
}
