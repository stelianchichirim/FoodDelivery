package Service;

import App.DriverPerson;
import App.Person;
import MyUtils.MyIO;

import java.util.ArrayList;
import java.util.List;

public class DriverService extends PersonService{

    private List<DriverPerson> drivers;

    private static DriverService instance = null;

    private DriverService() { drivers = new ArrayList<>(); }

    public static DriverService getInstance() {
        if (instance == null) instance = new DriverService();
        return instance;
    }

    @Override
    public String csvFilePath() {
        return "csvFiles/driver.csv";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Person driver : drivers) data.add(driver.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        drivers.add(new DriverPerson(data[0], data[1], Integer.parseInt(data[2])));
    }

    @Override
    public void newEntry() {
        String[] data = MyIO.getInstance().readLine().split(" ");
        create(data);
    }

    public DriverPerson getEntry(int id) {
        if (id < drivers.size()) return drivers.get(id);
        return null;
    }

    public void showPeople() {
        for (Person driver : drivers) driver.printInfo();
    }
}
