package danil;

import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    static ClassLoader fl = new ClassLoader();
    static Map<String, ClassData> map = ClassLoader.getMap();
    static int numberOfClasses;
    static ClassData lastData = null;
    private static String path = null;
    private static void printClassMap(String className){
        ClassData temp = map.get(className);
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

    private static void printClasses(String s){
        System.out.print("\nList of classes: ");
        System.out.print("\n----------------\n");
        fl.printArrayList();
        System.out.printf("\n\nEnter (1-%d) to view details or m for main menu: ",numberOfClasses);
    }

    private static void printClass(ClassLoader cl, String s){
        int i = Integer.parseInt(s);
        String name = cl.getClassNameTag(i);
        printClassMap(name);
        System.out.print("Enter s to save or m for Main Menu: ");
    }

    private static void saveFile() throws IOException {
        if(lastData!=null){
            serializeOneToXML(lastData);
            System.out.printf("\n\nSaved class information as %s.xml\n\n",lastData.getName());
            printMainMenu();
        }
        else{
            System.out.print("First choose the file to save!");
        }
    }

    private static void saveAllClasses() throws IOException {
        System.out.print("\n");
        serializeAllToXML();
        System.out.print("\nSaved all classes information in PackageDump.xml\n");
        printMainMenu();
    }

    private static void serializeOneToXML(ClassData data) throws IOException {
        FileOutputStream fos = new FileOutputStream(data.getName()+".xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            @Override
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :"+e.toString());
            }
        });
        encoder.writeObject(data);
        encoder.close();
        fos.close();
    }

    private static void serializeAllToXML () throws IOException {
        FileOutputStream fos = new FileOutputStream("PackageDump.xml");
        XMLEncoder encoder = new XMLEncoder(fos);

        for (Map.Entry<String, ClassData> entry : map.entrySet()) {
            String key = entry.getKey();
            ClassData value = entry.getValue();

            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception! :" + e.toString());
                }
            });

            encoder.writeObject(value);
            System.out.println(key + " class was written to PackageDump.xml");

        }

        encoder.close();
        fos.close();
    }

    private static void subMenu(){
        Scanner readerB = new Scanner(System.in);
        String b = readerB.next();
        switch (b){
            case "1": printClass(fl,b);
                break;
            case "2": printClass(fl,b);
                break;
            case "m":
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
        numberOfClasses = fl.getNumberOfClasses();
        printMainMenu();

        while(true){
            Scanner reader = new Scanner(System.in);
            String a = reader.next();

            switch (a){
                case "1":
                    printClasses(a);
                    subMenu();
                    break;
                case "2":
                    System.out.print("\nChoose a class to view: \n\n");
                    printClasses(a);
                    subMenu();
                    break;
                case "3": saveAllClasses();
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
