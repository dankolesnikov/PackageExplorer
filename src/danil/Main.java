package danil;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;


/**
 * @author Danil Kolesnikov danil.kolesnikov@sjsu.edu
 * CS 151 HW5 Fall 2017
 */


/**
 * Main class is responsible for command line interface i.e. starting point of the program.
 */

public class Main {

    static ClassLoader fl = new ClassLoader();
    static String lastFileName; // Contains the name of the class that was last accessed by the user. Used by saveFile() function
    private static String path; // Contains the path name. Ex: /Users/danil/Documents/coding/TestPackageExplorer/out/production/TestPackageExplorer
    private static Serialization serializer = new Serialization();

    // printClassFromMap gets a ClassData object from the HashMap and prints its content using object's internal function
    private static void printClassFromMap(String className){
        ClassData temp = DataMap.getMap().get(className);
        lastFileName = temp.getName();
        temp.printClass();
    }

    // Helper function that loads all class files into Hash Map of DataMap class using ClassLoader class.
    private static void load(ClassLoader loader, String path)  {
        //String test = "/Users/danil/Documents/coding/TestPackageExplorer/out/production/TestPackageExplorer";

        if (path.equals(System.getProperty("user.dir"))) {
            //loader.loadPackage(test);
            loader.loadPackage(System.getProperty("user.dir")); // Load files from current directory
        } else {
            loader.loadPackage(path); // Load Files from the argument
        }
    }

    // helper function to print Main menu contents
    private static void printMainMenu(){
        System.out.print("\n*** Package Explorer: Main Menu ***");
        System.out.print("\n-----------------------------------");
        System.out.print("\n   1. List all classes\n   2. View a class\n   3. Save all classes\n   4. Load classes info from XML");
        System.out.print("\n\nEnter your choice(1-4) or q to quit the program: ");
    }

    // Goes through the array list of Class names in DataMap function and prints out all class names
    private static void printClasses(){
        System.out.print("\nList of classes: ");
        System.out.print("\n----------------\n");

        // Print classes from classNames Array List
        for(int i = 1; i<DataMap.getClassNames().size(); i++){
            System.out.printf("\n%d. %s",i,DataMap.getClassNames().get(i));
        }

        System.out.printf("\n\nEnter (1-%d) to view details or m for main menu: ",DataMap.getClassNames().size()-1);
    }

    // Prints class information from DataMap hash map based on the numerical input from the user
    private static void printClass(String index){
        int i = Integer.parseInt(index);
        try {
            String className = DataMap.getClassNames().get(i);
            printClassFromMap(className);
            System.out.print("\nEnter s to save or m for Main Menu: ");
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            System.out.println("\nThis class doesn't exist. Try again!");
            printClasses();
            subMenu();
        }

    }

    // saveFile() function saves the Class Data object from the Hash Map in DataMap class with key value of lastFileName to an XML file in the current directory
    private static void saveFile() {
            serializer.serializeOneToXML(lastFileName);
            System.out.printf("\n\nSaved class information as %s.xml",lastFileName);
            System.out.print("\nFile was written in "+path);
            printMainMenu();
    }

    // saveAllClasses saves the HashMap object from the DataMap class to an XML file
    private static void saveAllClasses() {
        System.out.print("Enter a file name(without extension): ");
        Scanner readerB = new Scanner(System.in);
        String name = readerB.next();
        serializer.serializeMapToXML(name); // Serialize the HashMap object in XML file
        System.out.printf("\nSaved all classes information as %s.xml",name);
        System.out.printf("\nFile was written in %s\n\n",path);
        printMainMenu();
    }

    // loadSavedFile() function asks for the name of the xml file to load; tries to load it and if the file not - an error message will be displayed
    private static void loadSavedFile() {
        System.out.print("Enter a file name to load or m for main menu.");
        System.out.print("\nFile name (no need to provide .xml extension): ");
        Scanner readerB = new Scanner(System.in);
        String name = readerB.next();

        if(name.equals("m")){
        }
        else{
            try {
                serializer.deserializeMapFromXML(name);
                String fullName = name+".xml";
                System.out.printf("\nFile %s was successfully loaded. Explore!\n",fullName);
                printMainMenu();
            }
            catch (IOException e){
                System.out.println("\nFile not found! Try again: ");
                loadSavedFile();
            }
        }
    }

    // Helper function to print out a sub menu after all classes were displayed
    private static void subMenu(){
        Scanner readerB = new Scanner(System.in);
        String input = readerB.next();

        switch (input){
            case "m": printMainMenu();
                break;
            case "s": saveFile();
                break;
            default : printClass(input);
                break;
        }
    }

    // pathSetter sets a variable path to the correct input
    private static void pathSetter(String[] args){
        if(args.length != 1){
            path = System.getProperty("user.dir"); // empty argument
        }
        else{
            path = args[0];
        }
    }


    public static void main(String[] args) {

        pathSetter(args); // Set the name of the current path based on the first argument
        load(fl, path); // Load all classes in the path directory in the Hash Map of DataMap class
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
                case "3": saveAllClasses(); // Save a HashMap object into an XML file
                    break;
                case "4": loadSavedFile(); // Load a HashMap object of all classes from the current directory
                    break;
                case "s": saveFile(); // Saves a ClassData object from HashMap into XML file
                    break;
                case "m": printMainMenu(); // Prints main menu
                    break;
                case "q": return; // exit application
            }
        }
    }
}
