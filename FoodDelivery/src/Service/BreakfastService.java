package Service;

import App.BreakfastFood;
import App.DriverPerson;
import App.Food;
import App.Person;
import MyUtils.MyIO;
import MyUtils.MyLogger;

import java.io.IOException;
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
    public String tableName() {
        return "breakfastfood";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Food food : breakfast)
            if (food != null) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        breakfast.add(new BreakfastFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void newEntry() {
        System.out.println("Write the new Breakfast entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[size (int)],[vegan (boolean)],[description (String)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            create(data);
            MyLogger.getInstance().addLog("Create new Breakfast entry");
            MyIO.succesCommand();
        }
        catch (Exception e) {
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void update(int id, String[] data) {
        breakfast.set(id, new BreakfastFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void edit(int id) {
        System.out.println("Write the new Breakfast entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[size (int)],[vegan (boolean)],[description (String)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            update(id, data);
            MyLogger.getInstance().addLog("Update Breakfast entry");
            MyIO.succesCommand();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Object with this Id does not exist!\n");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void delete(int id) {
        if (id < breakfast.size() && breakfast.get(id) != null) {
            breakfast.remove(id);
            MyLogger.getInstance().addLog("Delete Breakfast entry");
            MyIO.succesCommand();
        }
        else {
            System.out.println("Object with this Id does not exist!");
        }
    }

    @Override
    public Food getEntry(int id) {
        if (id < breakfast.size()) return breakfast.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : breakfast)
            if (food != null) food.printInfo();
        MyLogger.getInstance().addLog("Show Breakfast entries");
    }

    public List<BreakfastFood> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<BreakfastFood> breakfast) {
        this.breakfast = breakfast;
    }
}
