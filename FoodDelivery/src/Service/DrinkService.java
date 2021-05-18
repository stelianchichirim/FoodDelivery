package Service;

import App.DrinkFood;
import App.Food;
import App.LunchFood;
import MyUtils.MyIO;
import MyUtils.MyLogger;

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
    public String tableName() {
        return "drinkfood";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Food food : drinks)
            if (food != null) data.add(food.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        drinks.add(new DrinkFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

    @Override
    public void newEntry() {
        System.out.println("Write the new Drink entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[capacity (int)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            create(data);
            MyLogger.getInstance().addLog("Create new Drink entry");
            MyIO.succesCommand();
        }
        catch (Exception e) {
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void update(int id, String[] data) {
        drinks.set(id, new DrinkFood(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
    }

    @Override
    public void edit(int id) {
        System.out.println("Write the new Drink entry in the following format:\n");
        System.out.println("[name (String)],[price (int)],[capacity (int)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            update(id, data);
            MyLogger.getInstance().addLog("Update Drink entry");
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
        if (id < drinks.size() && drinks.get(id) != null) {
            drinks.remove(id);
            MyLogger.getInstance().addLog("Delete Drink entry");
            MyIO.succesCommand();
        }
        else {
            System.out.println("Object with this Id does not exist!");
        }
    }

    @Override
    public Food getEntry(int id) {
        if (id < drinks.size()) return drinks.get(id);
        return null;
    }

    @Override
    public void showFood() {
        for (Food food : drinks)
            if (food != null) food.printInfo();
        MyLogger.getInstance().addLog("Show Drink entries");
    }

    public List<DrinkFood> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkFood> drinks) {
        this.drinks = drinks;
    }
}
