package Service;

import App.BreakfastFood;
import App.DinnerFood;
import App.Food;
import App.LunchFood;
import MyUtils.MyIO;
import MyUtils.MyLogger;

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
        for (Food food : dinner)
            if (food != null) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        dinner.add(new DinnerFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void newEntry() {
        System.out.println("Write the new Dinner entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[size (int)],[vegan (boolean)],[description (String)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            create(data);
            MyLogger.getInstance().addLog("Create new Dinner entry");
            MyIO.succesCommand();
        }
        catch (Exception e) {
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void update(int id, String[] data) {
        dinner.set(id, new DinnerFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void edit(int id) {
        System.out.println("Write the new Dinner entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[size (int)],[vegan (boolean)],[description (String)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            update(id, data);
            MyLogger.getInstance().addLog("Update Dinner entry");
            MyIO.succesCommand();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Object with this Id does not exist!\n");
        }
        catch (Exception e) {
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void delete(int id) {
        if (id < dinner.size() && dinner.get(id) != null) {
            dinner.remove(id);
            MyLogger.getInstance().addLog("Delete Dinner entry");
            MyIO.succesCommand();
        }
        else {
            System.out.println("Object with this Id does not exist!");
        }
    }

    @Override
    public Food getEntry(int id) {
        if (id < dinner.size()) return dinner.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : dinner)
            if (food != null) food.printInfo();
        MyLogger.getInstance().addLog("Show Dinner entries");
    }

    public List<DinnerFood> getDinner() {
        return dinner;
    }

    public void setDinner(List<DinnerFood> dinner) {
        this.dinner = dinner;
    }
}
