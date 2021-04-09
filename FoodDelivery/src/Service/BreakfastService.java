package Service;

import App.BreakfastFood;
import App.DriverPerson;
import App.Food;
import App.Person;
import MyUtils.MyIO;

import java.util.ArrayList;
import java.util.List;

public class BreakfastService extends FoodService {

    private List<BreakfastFood> breakfast;

    private static BreakfastService instance = null;

    private BreakfastService() { breakfast = new ArrayList<>(); }

    public static BreakfastService getInstance() {
        if (instance == null) instance = new BreakfastService();
        return instance;
    }

    @Override
    public String csvFilePath() {
        return "csvFiles/breakfast.csv";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Food food : breakfast) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        breakfast.add(new BreakfastFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void newEntry() {
        String[] data = MyIO.getInstance().readLine().split(" ");
        create(data);
    }

    @Override
    public Food getEntry(int id) {
        if (id < breakfast.size()) return breakfast.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : breakfast) food.printInfo();
    }
}
