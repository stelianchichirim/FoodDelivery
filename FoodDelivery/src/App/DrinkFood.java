package App;

import MyUtils.MyIO;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
