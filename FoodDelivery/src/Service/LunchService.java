package Service;

import App.Food;
import App.LunchFood;
import MyUtils.MyIO;

import java.util.ArrayList;
import java.util.List;

public class LunchService extends FoodService {

    private List<LunchFood> lunch;

    private static LunchService instance = null;

    private LunchService() { lunch = new ArrayList<>(); }

    public static LunchService getInstance() {
        if (instance == null) instance = new LunchService();
        return instance;
    }

    @Override
    public String csvFilePath() {
        return "csvFiles/lunch.csv";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Food food : lunch) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        lunch.add(new LunchFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void newEntry() {
        String[] data = MyIO.getInstance().readLine().split(" ");
        create(data);
    }

    @Override
    public Food getEntry(int id) {
        if (id < lunch.size()) return lunch.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : lunch) food.printInfo();
    }

    public List<LunchFood> getLunch() {
        return lunch;
    }

    public void setLunch(List<LunchFood> lunch) {
        this.lunch = lunch;
    }
}
