package MyUtils;

import java.util.Scanner;
// import java.io.*;

public class MyIO {

    private final Scanner scanner;

    private static MyIO instance = null;

    private MyIO() {
        scanner = new Scanner(System.in);
    }

    public static MyIO getInstance() {
        if (instance == null) instance = new MyIO();
        return instance;
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public String[] readCommands() {
        return scanner.nextLine().split(" ");
    }

    public String readString() {
        return scanner.next();
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public static void separator() {
        System.out.println("\n========================\n");
    }

    public static void wrongCommand() {
        System.out.println("Wrong command!\n");
    }

    public static void succesCommand() {
        System.out.println("The command was successful!\n");
    }

}
