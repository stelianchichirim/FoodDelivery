package Service;

import App.DrinkFood;
import App.Food;
import App.LunchFood;
import MyUtils.MyIO;

import java.util.ArrayList;
import java.util.List;

public class DrinkService extends FoodService {

    private List<DrinkFood> drinks;

    private static DrinkService instance = null;

    private DrinkService() { drinks = new ArrayList<>(); }

    public static DrinkService getInstance() {
        if (instance == null) instance = new DrinkService();
        return instance;
    }

    @Override
    public String csvFilePath() {
        return "csvFiles/drink.csv";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Food food : drinks) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        drinks.add(new DrinkFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

    @Override
    public void newEntry() {
        String[] data = MyIO.getInstance().readLine().split(" ");
        create(data);
    }

    @Override
    public Food getEntry(int id) {
        if (id < drinks.size()) return drinks.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : drinks) food.printInfo();
    }

}
