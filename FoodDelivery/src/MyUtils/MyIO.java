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

    public String readString() {
        return scanner.next();
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public static void separator() {
        System.out.println("\n========================\n");
    }

}
