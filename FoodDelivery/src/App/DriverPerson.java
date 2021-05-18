package App;

import MyUtils.MyIO;

public class DriverPerson extends Person{

    private int salary;

    public DriverPerson(String _firstName, String _lastName, int _salary) {
        super(_firstName, _lastName);
        salary = _salary;
    }

    @Override
    public void printInfo() {
        System.out.println(firstName + " " + lastName);
        System.out.println("Salary: " + salary + " lei");
        MyIO.separator();
    }

    @Override
    public String[] parseToList() {
        return new String[] {firstName, lastName, Integer.toString(salary)};
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
