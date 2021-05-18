package Service;

import App.DriverPerson;
import App.Person;
import MyUtils.MyIO;
import MyUtils.MyLogger;

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
    public String tableName() {
        return "driverperson";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Person driver : drivers)
            if (driver != null) data.add(driver.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        drivers.add(new DriverPerson(data[0], data[1], Integer.parseInt(data[2])));
    }

    @Override
    public void newEntry() {
        System.out.println("Write the new Driver entry in the following format:\n");
        System.out.println("[firstName (String)],[lastName (String)],[salary (int)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            create(data);
            MyLogger.getInstance().addLog("Create new Driver entry");
            MyIO.succesCommand();
        }
        catch (Exception e) {
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void update(int id, String[] data) {
        drivers.set(id, new DriverPerson(data[0], data[1], Integer.parseInt(data[2])));
    }

    @Override
    public void edit(int id) {
        System.out.println("Write the new Driver entry in the following format:\n");
        System.out.println("[firstName (String)],[lastName (String)],[salary (int)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            update(id, data);
            MyLogger.getInstance().addLog("Update Driver entry");
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
        if (id < drivers.size() && drivers.get(id) != null) {
            drivers.remove(id);
            MyLogger.getInstance().addLog("Delete Driver entry");
            MyIO.succesCommand();
        }
        else {
            System.out.println("Object with this Id does not exist!");
        }
    }

    public DriverPerson getEntry(int id) {
        if (id < drivers.size()) return drivers.get(id);
        return null;
    }

    public void showPeople() {
        for (Person driver : drivers)
            if (driver != null) driver.printInfo();
        MyLogger.getInstance().addLog("Show Driver entries");
    }

    public List<DriverPerson> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverPerson> drivers) {
        this.drivers = drivers;
    }
}
