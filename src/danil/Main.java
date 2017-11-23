package danil;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class Main {

    static ClassLoader fl = new ClassLoader();
    static ClassData lastData = null;
   // private static ArrayList<String> classNames = DataMap.getClassNames();
    private static String path;
    private static Serialization serializer = new Serialization();

    private static void printClassFromMap(String className){
        ClassData temp = DataMap.getMap().get(className);
        lastData = temp;
        temp.printClass();
    }

    private static void load(ClassLoader loader, String path) throws IOException, ClassNotFoundException {
        String test = "/Users/danil/Documents/coding/TestPackageExplorer/out/production/TestPackageExplorer";
        if (path.equals("")) {
            loader.loadPackage(test);
            //loader.loadPackage(System.getProperty("user.dir")); // Load files from current directory
        } else {
            loader.loadPackage(path); // Load Files from the argument
        }
    }

    private static void printMainMenu(){
        System.out.print("\n*** Package Explorer: Main Menu ***");
        System.out.print("\n-----------------------------------");
        System.out.print("\n   1. List all classes\n   2. View a class\n   3. Save all classes\n   4. Load classes info from XML");
        System.out.print("\n\nEnter your choice(1-4) or q to quit the program: ");
    }

    private static void printClasses(){
        System.out.print("\nList of classes: ");
        System.out.print("\n----------------\n");

        // Print classes from classNames Array List
        for(int i = 1; i<DataMap.getClassNames().size(); i++){
            System.out.printf("\n%d. %s",i,DataMap.getClassNames().get(i));
        }

        System.out.printf("\n\nEnter (1-%d) to view details or m for main menu: ",DataMap.getClassNames().size()-1);
    }

    private static void printClass(String index){
        int i = Integer.parseInt(index);
        String className = DataMap.getClassNames().get(i);
        printClassFromMap(className);
        System.out.print("Enter s to save or m for Main Menu: ");
    }

    private static void saveFile() throws IOException {
        if(lastData!=null){
            serializer.serializeOneToXML(lastData);
            System.out.printf("\n\nSaved class information as %s.xml\n\n",lastData.getName());
            System.out.print("\nFile was written in "+path);
            printMainMenu();
        }
        else{
            System.out.print("First choose the file to save!");
        }
    }

    private static void saveAllClasses() throws IOException {
        System.out.print("Enter a file name: ");
        Scanner readerB = new Scanner(System.in);
        String name = readerB.next();
        serializer.serializeMapToXML(name);
        printMainMenu();
    }

    private static void loadSavedFile() throws IOException {
        System.out.print("Enter a file name to load. No need to provide .xml extension: ");
        Scanner readerB = new Scanner(System.in);
        String name = readerB.next();
        serializer.deserializeFromXML(name);
        printMainMenu();
    }

    private static void subMenu(){
        Scanner readerB = new Scanner(System.in);
        String input = readerB.next();

        switch (input){
            case "m": printMainMenu();
                break;
            default : printClass(input);
                break;
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(args.length != 1){
            path = "";
        }
        else{
            path = args[0];
        }
        load(fl, path);
        DataMap.fillClassNames();
        printMainMenu();

        while(true){
            Scanner reader = new Scanner(System.in);
            String a = reader.next();

            switch (a){
                case "1":
                    printClasses();
                    subMenu();
                    break;
                case "2":
                    System.out.print("\nChoose a class to view: \n\n");
                    printClasses();
                    subMenu();
                    break;
                case "3": saveAllClasses();
                    break;
                case "4": loadSavedFile();
                    break;
                case "s": saveFile();
                    break;
                case "m": printMainMenu();
                    break;
                case "q": return;
            }
        }
    }
}
