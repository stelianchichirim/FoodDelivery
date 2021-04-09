package Service;

import App.Food;
import App.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonService {

    // Get the csv File Path for the this object type
    public abstract String csvFilePath();

    // Parse the data to write it in csv
    public abstract List<String[]> parseData();

    // Create a new entry of the class, using data
    public abstract void create(String[] data);

    // Create a new entry of the class, using stdin information
    public abstract void newEntry();

    // Print information for each entry of that class
    public abstract void showPeople();

    // Print information about all the people
    public static void showAllPeople() {
        for (PersonService service : getServiceInstances()) service.showPeople();
    }

    // Get the instances for all class services that use csv File as a database
    public static ArrayList<PersonService> getServiceInstances() {
        ArrayList<PersonService> serviceInstances = new ArrayList<>();

        serviceInstances.add(UserService.getInstance());
        serviceInstances.add(DriverService.getInstance());

        return serviceInstances;
    }

}
