package MyUtils;

// using csv as a database

import Service.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyCsvDataBase {

    private BufferedReader csvReader;
    private FileWriter csvWriter;

    private static MyCsvDataBase instance = null;

    private MyCsvDataBase() { }

    public static MyCsvDataBase getInstance() {
        if (instance == null) instance = new MyCsvDataBase();
        return instance;
    }

    public List<String[]> readCsvFile(String csvFilePath) {
        List<String[]> lineData = new ArrayList<>();
        String currLine = "";
        try {
            csvReader = new BufferedReader(new FileReader(csvFilePath));

            while ( (currLine = csvReader.readLine()) != null) {
                lineData.add(currLine.split(","));
            }

            csvReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return lineData;
    }

    public void writeCsvFile(String csvFilePath, List<String[]> rowCsvData) {
        try {
            csvWriter = new FileWriter(csvFilePath);

            for (String[] data : rowCsvData) {
                csvWriter.append(String.join(",", data));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromCsv() {

        // load csv data for persons
        for (PersonService classService : PersonService.getServiceInstances()) {
            List<String[]> csvData = readCsvFile(classService.csvFilePath());
            for (String[] entry : csvData) {
                classService.create(entry);
            }
        }

        // load csv data for food
        for (FoodService classService : FoodService.getServiceInstances()) {
            List<String[]> csvData = readCsvFile(classService.csvFilePath());
            for (String[] entry : csvData) {
                classService.create(entry);
            }
        }

        // load csv data for logger
        List<String[]> csvData = readCsvFile(MyLogger.getInstance().csvFilePath());
        for (String[] entry : csvData) {
            MyLogger.getInstance().create(entry);
        }
    }

    public void loadDataInCsv() {

        // load csv data for persons
        for (PersonService classService : PersonService.getServiceInstances()) {
            writeCsvFile(classService.csvFilePath(), classService.parseData());
        }

        // load csv data for food
        for (FoodService classService : FoodService.getServiceInstances()) {
            writeCsvFile(classService.csvFilePath(), classService.parseData());
        }

        // load csv data for logger
        writeCsvFile(MyLogger.getInstance().csvFilePath(), MyLogger.getInstance().parseData());
    }

}
