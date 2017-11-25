package danil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Danil Kolesnikov danil.kolesnikov@sjsu.edu
 * CS 151 HW5 Fall 2017
 */

/**
 * DataMap class is responsible for storing the current Data Set of class information to display to the user
 */
public class DataMap {

    private static HashMap<String, ClassData> map = new HashMap<>(); // Contains all the current data is stored
    private static ArrayList<String> classNames = new ArrayList<>(); // Contains the names of classes or key values of HashMap

    // Empty constructor
    public DataMap(){

    }

    /* Getters */
    public static ArrayList<String> getClassNames(){
        return classNames;
    }
    public static HashMap<String, ClassData> getMap() {
        return map;
    }

    // setMap() function sets a new HashMap object to the current map variable; resets classNames array list and fills it up based on the current map
    public static void setMap(HashMap<String, ClassData> newMap){
        map = newMap;
        resetClassNames(classNames); // Clear the classNames array list before filling it up
        fillClassNames(); // Fill up the aray list with new class names
    }

    // Helper function to fill array list of class names from the HashMap
    public static void fillClassNames(){
        classNames.add(0,null);
        int i = 1;
        for (Map.Entry<String, ClassData> entry : map.entrySet()) {
            String name = entry.getKey();
            classNames.add(i,name);
            i++;
        }
    }

    // Helper function to clear the array list
    public static void resetClassNames(ArrayList<String> list){
        list.clear();
    }


}
