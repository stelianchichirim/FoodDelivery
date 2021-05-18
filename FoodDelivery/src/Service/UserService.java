package Service;

import App.Person;
import App.UserPerson;
import MyUtils.MyIO;
import MyUtils.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class UserService extends PersonService {

    private List<UserPerson> users;

    private static UserService instance = null;

    private UserService() { users = new ArrayList<>(); }

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    @Override
    public String csvFilePath() {
        return "csvFiles/user.csv";
    }

    @Override
    public String tableName() {
        return "userperson";
    }

    @Override
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Person user : users)
            if (user != null) data.add(user.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        users.add(new UserPerson(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
    }

    @Override
    public void newEntry() {
        System.out.println("Write the new User entry in the following format:\n");
        System.out.println("[firstName (String)],[lastName (String)],[discountProcent (int)], [nrOrders (int)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            create(data);
            MyLogger.getInstance().addLog("Create new User entry");
            MyIO.succesCommand();
        }
        catch (Exception e) {
            System.out.println("Wrong format!\n");
        }
    }

    @Override
    public void update(int id, String[] data) {
        users.set(id, new UserPerson(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
    }

    @Override
    public void edit(int id) {
        System.out.println("Write the new User entry in the following format:\n");
        System.out.println("[firstName (String)],[lastName (String)],[discountProcent (int)], [nrOrders (int)]\n");
        String[] data = MyIO.getInstance().readLine().split(",");
        try {
            update(id, data);
            MyLogger.getInstance().addLog("Update User entry");
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
        if (id < users.size() && users.get(id) != null) {
            users.remove(id);
            MyLogger.getInstance().addLog("Delete User entry");
            MyIO.succesCommand();
        }
        else {
            System.out.println("Object with this Id does not exist!");
        }
    }

    public UserPerson getEntry(int id) {
        if (id < users.size()) return users.get(id);
        return null;
    }

    public void showPeople() {
        for (Person user : users)
            if (user != null) user.printInfo();
        MyLogger.getInstance().addLog("Show User entries");
    }

    public List<UserPerson> getUsers() {
        return users;
    }

    public void setUsers(List<UserPerson> users) {
        this.users = users;
    }
}
