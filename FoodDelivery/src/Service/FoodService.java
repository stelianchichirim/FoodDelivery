package Service;

import App.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodService {

    // Get the csv File Path for the this object type
    public abstract String csvFilePath();

    // Parse the data to write it in csv
    public abstract List<String[]> parseData();

    // Create a new entry of the class, using data
    public abstract void create(String[] data);

    // Create a new entry of the class, using stdin information
    public abstract void newEntry();

    // Update an entry using data
    public abstract void update(int id, String[] data);

    // Edit an existing entry
    public abstract void edit(int id);

    // Delete an existing entry
    public abstract void delete(int id);

    // Get the entry from position id
    public abstract Food getEntry(int id);

    // Print information for each entry of that class
    public abstract void showFood();

    // Print information about all the foods
    public static void showAllFood() {
        for (FoodService service : getServiceInstances()) service.showFood();
    }

    // Get the instances for all Food class services that use csv File as a database
    public static ArrayList<FoodService> getServiceInstances() {
        ArrayList<FoodService> serviceInstances = new ArrayList<>();

        serviceInstances.add(BreakfastService.getInstance());
        serviceInstances.add(LunchService.getInstance());
        serviceInstances.add(DinnerService.getInstance());
        serviceInstances.add(DrinkService.getInstance());

        return serviceInstances;
    }

}
