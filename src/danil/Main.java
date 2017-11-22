package danil;

import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import danil.ClassLoader;

public class Main {

    static ClassLoader fl = new ClassLoader();
    static Map<String, ClassData> map = ClassLoader.getMap();
    static int numberOfClasses;
    static ClassData lastData;

    public static void printClassMap(String className){
        ClassData temp = map.get(className);
        lastData = temp;
        temp.printClass();
    }

    public static void load(ClassLoader loader, String[] args) throws IOException, ClassNotFoundException {
        String test = "/Users/danil/Documents/coding/TestPackageExplorer/out/production/TestPackageExplorer";
        if (args.length == 0) {
           loader.loadPackage(test);
            //loader.loadPackage(System.getProperty("user.dir")); // Load files from current directory

        } else {
            loader.loadPackage(args[0]); // Load Files from the argument
        }
    }

    public static void printMainMenu(){
        System.out.print("\n*** Package Explorer: Main Menu ***");
        System.out.print("\n-----------------------------------");
        System.out.print("\n1. List all classes\n2. View a class\n3. Save all classes\n4. Load classes info from XML");
        System.out.print("\n\nEnter your choice(1-4) or q to quit the program: ");
    }

    public static void printClasses(String s){
        System.out.print("List of classes: ");
        System.out.print("\n----------------\n");
        fl.printArrayList();
        System.out.printf("\nEnter (1-%d) to view details or m for main menu: ",numberOfClasses);
    }

    public static void printClass(ClassLoader cl, String s){
        int i = Integer.parseInt(s);
        String name = cl.getClassNameTag(i);
        printClassMap(name);
        System.out.print("Enter s to save or m for Main Menu: ");
    }

    public static void saveFile() throws IOException {
        serializeToXML(lastData);
        System.out.printf("\n\nSaved class information as %d.xml\n\n",lastData);
        printMainMenu();
    }
    public static void saveAllClasses() throws IOException {
        System.out.print("\n");
        serializeAllToXML();
        System.out.print("\nSaved all classes information in PackageDump.xml\n");
        printMainMenu();
    }

    public static void serializeToXML (ClassData data) throws IOException {
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

    public static void serializeAllToXML () throws IOException {
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        load(fl, args);
        numberOfClasses = fl.getNumberOfClasses();
        printMainMenu();

        while(true){
            Scanner reader = new Scanner(System.in);
            String a = reader.next();

            switch (a){
                case "1":
                    printClasses(a);
                    Scanner readerB = new Scanner(System.in);
                    String b = readerB.next();
                    switch (b){
                        case "1": printClass(fl,b);
                            break;
                        case "2": printClass(fl,b);
                            break;
                    }
                    break;
                case "2":
                    printClasses(a);
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
