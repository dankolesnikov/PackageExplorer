package danil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by danil on 11/22/17.
 */
public class DataMap {

    private static HashMap<String, ClassData> map = new HashMap<>(); // Where all the data is stored
    private static ArrayList<String> classNames = new ArrayList<>();

    public static ArrayList<String> getClassNames(){
        return classNames;
    }
    public static HashMap<String, ClassData> getMap() {
        return map;
    }

    public static void setMap(HashMap<String, ClassData> newMap){
        map = newMap;
        resetClassNames();
        fillClassNames();
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

    public static void resetClassNames(){
        classNames.clear();
    }


}
