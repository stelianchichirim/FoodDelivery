package MyUtils;

import App.DinnerFood;
import App.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Logg {

    private final String logInfo;
    private final String timeStamp;

    public Logg(String _logInfo, String _timeStamp) {
        logInfo = _logInfo;
        timeStamp = _timeStamp;
    }

    public void printInfo() {
        System.out.println(timeStamp + ": " + logInfo + "\n");
    }

    public String[] parseToList() {
        return new String[] {logInfo, timeStamp};
    }

}



public class MyLogger {

    private List<Logg> logs;

    private static MyLogger instance = null;

    private MyLogger() {
        logs = new ArrayList<>();
    }

    public static MyLogger getInstance() {
        if (instance == null) instance = new MyLogger();
        return instance;
    }

    public String csvFilePath() {
        return "csvFiles/logger.csv";
    }

    public List<String[]> parseData() {
        List<String[]> data = new ArrayList<>();
        for (Logg log : logs) data.add(log.parseToList());
        return data;
    }

    public void create(String[] data) {
        logs.add(new Logg(data[0], data[1]));
    }

    public void addLog(String info) {
        logs.add(new Logg(info, Calendar.getInstance().getTime().toString()));
    }

    public void showLogger() {
        for (Logg log : logs) log.printInfo();
        MyIO.separator();
    }

}
