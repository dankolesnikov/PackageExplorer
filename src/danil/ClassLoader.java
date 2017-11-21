package danil;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassLoader {

    /** Constructor */
    ClassLoader(){
        initList();
    }

    static private HashMap<String,ClassData> map = new HashMap<>();
    private int numberOfClasses;
    private ArrayList<String> classes = new ArrayList<>(10);


    private void initList(){
        for(int i = 0; i<10;i++){
            classes.add(i,null);
        }
    }
    /** Getters */
    public int getNumberOfClasses(){
        return numberOfClasses;
    }

    public String getClassNameTag(int i){
        return classes.get(i);
    }

    public static HashMap<String, ClassData> getMap() {
        return map;
    }

    /** Setters */
    public void setNumberOfClasses(int i){
        this.numberOfClasses = i-1;
    }

    private void setClassNameTag(int i, String nameTag){
        classes.add(i,nameTag);
    }

    // Main method that loads a package
    public void loadPackage(String path) throws IOException, ClassNotFoundException {

        FilenameFilter classFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".class");
            }
        };

        File f = new File(path);
        int i = 1;
        // Go through class files and remove .class extension; Fill up HashMap
        for ( File file : f.listFiles(classFilter) ) {
            //System.out.println(i+". "+file.getName().replace(".class", ""));
            String fileName = file.getName().replace(".class", "");
            setClassNameTag(i,fileName);
            Class c = loadClass(fileName,path);
            String className = loadName(c);
            String superClassName = loadSuperClassName(c);
            String[] classInterface = loadInterfaces(c);
            String[] classFields = loadFields(c);
            String[] classMethods = loadMethods(c);
            generateClassData(i,className,superClassName,classInterface,classFields,classMethods);

            i++;
        }
        setNumberOfClasses(i);
    }

    public void generateClassData(int position, String name, String superClassName, String[] interfaces, String[] fields, String[] methods){

        ClassData newClass = new ClassData();
        newClass.setNumber(position);
        newClass.setName(name);
        newClass.setSuperClassName(superClassName);
        newClass.setInterfaces(interfaces);
        newClass.setFields(fields);
        newClass.setMethods(methods);

        map.put(name,newClass);
    }


    private Class loadClass(String name, String path) throws ClassNotFoundException, IOException {
        String url = "file://"+path+"/";
        //URL classUrl = new URL("file:///Users/danil/Documents/coding/TestPackageExplorer/out/production/TestPackageExplorer/");
        URL classUrl = new URL(url);
        //System.out.print(classUrl.toString());
        URLClassLoader ucl = new URLClassLoader(new URL[]{classUrl});

        return ucl.loadClass(name);
    }

    private String[] loadFields(Class c) {

        Field[] fields = c.getDeclaredFields();
        String[] results = new String[fields.length];
        int i = 0;
        for (Field field: fields) {
            results[i] = field.getName();
            i++;
        }
        return results;
    }

    private String[] loadMethods(Class c) {
        Method[] methods = c.getDeclaredMethods();
        String[] results = new String[methods.length];
        int i = 0;
        for (Method method: methods){
            int m = method.getModifiers();
            String mod = Modifier.toString(m);
            results[i] = method.getName()+": "+mod;
            i++;
        }
        return results;
    }

    private String loadName(Class c){
        String result = c.getName();
        return result;
    }

    private String loadSuperClassName(Class c){
        String result = c.getSuperclass().getSimpleName();
        return result;
    }

    private String[] loadInterfaces(Class c){
        Class[] interfaces = c.getInterfaces();
        String[] result = new String[interfaces.length];

        int i = 0;
        for(Class cl: interfaces){
            result[i] = cl.getName();
        }
        return result;
    }

    public void printArrayList(){
        for(int i = 1; i < classes.size(); i++){
            String temp = getClassNameTag(i);
            if(temp!=null){
                System.out.print("\n"+i+". "+temp);
            }
        }
    }

}
