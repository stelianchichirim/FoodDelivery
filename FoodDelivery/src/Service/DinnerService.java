package Service;

import App.DinnerFood;
import App.Food;
import App.LunchFood;
import MyUtils.MyIO;

import java.util.ArrayList;
import java.util.List;

public class DinnerService extends FoodService {

    private List<DinnerFood> dinner;

    private static DinnerService instance = null;

    private DinnerService() { dinner = new ArrayList<>(); }

    public static DinnerService getInstance() {
        if (instance == null) instance = new DinnerService();
        return instance;
    }

    @Override
    public String csvFilePath() {
        return "csvFiles/dinner.csv";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Food food : dinner) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        dinner.add(new DinnerFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void newEntry() {
        String[] data = MyIO.getInstance().readLine().split(" ");
        create(data);
    }

    @Override
    public Food getEntry(int id) {
        if (id < dinner.size()) return dinner.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : dinner) food.printInfo();
    }

    public List<DinnerFood> getDinner() {
        return dinner;
    }

    public void setDinner(List<DinnerFood> dinner) {
        this.dinner = dinner;
    }
}
