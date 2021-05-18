package MyUtils;

import Service.FoodService;
import Service.PersonService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDataBase {

    private static String jdbcUrl = "jdbc:mysql://localhost:3306/fooddeliverydb";
    private static String username = "root";
    private static String password = "root";

    private static MySqlDataBase instance = null;

    private MySqlDataBase() { }

    public static MySqlDataBase getInstance() {
        if (instance == null) instance = new MySqlDataBase();
        return instance;
    }

    public void initializeTables() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            // create tables for persons
            for (PersonService classService : PersonService.getServiceInstances()) {
                String sqlQuery = "CREATE TABLE IF NOT EXISTS " + classService.tableName() + "( "
                                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                + "content VARCHAR(300) NOT NULL" + " )";

                statement.executeUpdate(sqlQuery);
            }

            // create tables for food
            for (FoodService classService : FoodService.getServiceInstances()) {
                String sqlQuery = "CREATE TABLE IF NOT EXISTS " + classService.tableName() + "( "
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "content VARCHAR(300) NOT NULL" + " )";

                statement.executeUpdate(sqlQuery);
            }

            // create table for logger
            String sqlQuery = "CREATE TABLE IF NOT EXISTS " + MyLogger.getInstance().tableName() + "( "
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "content VARCHAR(300) NOT NULL" + " )";

            statement.executeUpdate(sqlQuery);

        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteAllEntries() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            // delete entries from tables for persons
            for (PersonService classService : PersonService.getServiceInstances()) {
                String sqlQuery = "DELETE FROM " + classService.tableName();
                statement.executeUpdate(sqlQuery);
            }

            // delete entries from tables for food
            for (FoodService classService : FoodService.getServiceInstances()) {
                String sqlQuery = "DELETE FROM " + classService.tableName();
                statement.executeUpdate(sqlQuery);
            }

            // delete entries from table for logger
            String sqlQuery = "DELETE FROM " + MyLogger.getInstance().tableName();
            statement.executeUpdate(sqlQuery);

        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public void loadDataFromJDBC() {
        // initializam tabelele
        initializeTables();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            // load data for persons
            for (PersonService classService : PersonService.getServiceInstances()) {
                String sqlQuery = "SELECT content FROM " + classService.tableName();
                ResultSet entries = statement.executeQuery(sqlQuery);
                List<String[]> csvData = new ArrayList<>();

                while (entries.next()) {
                    String[] listContent = entries.getString("content").split(",");
                    csvData.add(listContent);
                }

                for (String[] entry : csvData) {
                    classService.create(entry);
                }
            }

            // load data for food
            for (FoodService classService : FoodService.getServiceInstances()) {
                String sqlQuery = "SELECT content FROM " + classService.tableName();
                ResultSet entries = statement.executeQuery(sqlQuery);
                List<String[]> csvData = new ArrayList<>();

                while (entries.next()) {
                    String[] listContent = entries.getString("content").split(",");
                    csvData.add(listContent);
                }

                for (String[] entry : csvData) {
                    classService.create(entry);
                }
            }

            // load data for logger
            String sqlQuery = "SELECT content FROM " + MyLogger.getInstance().tableName();
            ResultSet entries = statement.executeQuery(sqlQuery);
            List<String[]> csvData = new ArrayList<>();

            while (entries.next()) {
                String[] listContent = entries.getString("content").split(",");
                csvData.add(listContent);
            }

            for (String[] entry : csvData) {
                MyLogger.getInstance().create(entry);
            }

        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public void loadDataInJDBC() {
        // initializam tabelele
        initializeTables();

        // stergem datele din tabele pentru a adauga cele noi in locul lor
        deleteAllEntries();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            // load data for persons
            for (PersonService classService : PersonService.getServiceInstances()) {
                List<String[]> entries = classService.parseData();
                for (String[] entry : entries) {
                    String csvEntry = String.join(",", entry);
                    String sqlQuery = "INSERT INTO " + classService.tableName()
                                    + " (content) "
                                    + " VALUES('" + csvEntry + "')";
                    statement.executeUpdate(sqlQuery);
                }
            }

            // load data for food
            for (FoodService classService : FoodService.getServiceInstances()) {
                List<String[]> entries = classService.parseData();
                for (String[] entry : entries) {
                    String csvEntry = String.join(",", entry);
                    String sqlQuery = "INSERT INTO " + classService.tableName()
                            + " (content) "
                            + " VALUES('" + csvEntry + "')";
                    statement.executeUpdate(sqlQuery);
                }
            }

            // load data for logger
            List<String[]> entries = MyLogger.getInstance().parseData();
            for (String[] entry : entries) {
                String csvEntry = String.join(",", entry);
                String sqlQuery = "INSERT INTO " + MyLogger.getInstance().tableName()
                        + " (content) "
                        + " VALUES('" + csvEntry + "')";
                statement.executeUpdate(sqlQuery);
            }

        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public void create(String tableName, String[] contentList) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            String contentCsv = String.join(",", contentList);
            String sqlQuery = "INSERT INTO " + tableName
                            + " (content) "
                            + " VALUES('" + contentCsv + "')";
            statement.executeUpdate(sqlQuery);
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public void update(String tableName, int id, String[] newContentList) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            String contentCsv = String.join(",", newContentList);
            String sqlQuery = "UPDATE " + tableName
                            + "SET content='" + contentCsv + "'"
                            + " WHERE id=" + id;
            statement.executeUpdate(sqlQuery);
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    public void delete(String tableName, int id) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            String sqlQuery = "DELETE FROM " + tableName
                    + " WHERE id=" + id;
            statement.executeUpdate(sqlQuery);
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

}
