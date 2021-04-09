package Service;

import App.Person;
import App.UserPerson;
import MyUtils.MyIO;

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
    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Person user : users) data.add(user.parseToList());
        return data;
    }

    @Override
    public void create(String[] data) {
        users.add(new UserPerson(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
    }

    @Override
    public void newEntry() {
        String[] data = MyIO.getInstance().readLine().split(" ");
        create(data);
    }

    public UserPerson getEntry(int id) {
        if (id < users.size()) return users.get(id);
        return null;
    }

    public void showPeople() {
        for (Person user : users) user.printInfo();
    }

    public List<UserPerson> getUsers() {
        return users;
    }

    public void setUsers(List<UserPerson> users) {
        this.users = users;
    }
}
