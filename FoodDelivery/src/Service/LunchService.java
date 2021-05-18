package Service;

import App.Food;
import App.LunchFood;
import MyUtils.MyIO;
import MyUtils.MyLogger;

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
    public String tableName() {
        return "lunchfood";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Food food : lunch)
            if (food != null) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        lunch.add(new LunchFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void newEntry() {
        System.out.println("Write the new Lunch entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[size (int)],[vegan (boolean)],[description (String)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            create(data);
            MyLogger.getInstance().addLog("Create new Lunch entry");
            MyIO.succesCommand();
        }
        catch (Exception e) {
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void update(int id, String[] data) {
        lunch.set(id, new LunchFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]), data[4]));
    }

    @Override
    public void edit(int id) {
        System.out.println("Write the new Lunch entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[size (int)],[vegan (boolean)],[description (String)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            update(id, data);
            MyLogger.getInstance().addLog("Update Lunch entry");
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
        if (id < lunch.size() && lunch.get(id) != null) {
            lunch.remove(id);
            MyLogger.getInstance().addLog("Delete Lunch entry");
            MyIO.succesCommand();
        }
        else {
            System.out.println("Object with this Id does not exist!");
        }
    }

    @Override
    public Food getEntry(int id) {
        if (id < lunch.size()) return lunch.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : lunch)
            if (food != null) food.printInfo();
        MyLogger.getInstance().addLog("Show Lunch entries");
    }

    public List<LunchFood> getLunch() {
        return lunch;
    }

    public void setLunch(List<LunchFood> lunch) {
        this.lunch = lunch;
    }
}
