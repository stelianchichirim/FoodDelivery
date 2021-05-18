package App;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {

    protected String firstName;
    protected String lastName;

    public Person(String _firstName, String _lastName) {
        firstName = _firstName;
        lastName = _lastName;
    }

    // Print all the information about the Person
    public abstract void printInfo();

    // Put all the fields in a list
    public abstract String[] parseToList();

}
