package danil;

import java.util.ArrayList;

/**
 * Created by danil on 11/21/17.
 */
public class ClassData {

    private String name;
    private String superClassName;
    private String[] interfaces;
    private String[] fields;
    private String[] methods;
    private String[] providers;
    private String[] clients;
    private ArrayList<String> classNames;

    public ClassData(){

    }
    /** Getters */

    public String getName() {
        return name;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public String[] getInterfaces() {
        return interfaces;
    }

    public String[] getFields() {
        return fields;
    }

    public String[] getMethods() {
        return methods;
    }

    public ArrayList<String> getClassNames() {
        return classNames;
    }

    public String[] getProviders() {
        return providers;
    }

    public String[] getClients() {
        return clients;
    }


    /** Setters */

    public void setName(String name) {
        this.name = name;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public void setInterfaces(String[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public void setMethods(String[] methods) {
        this.methods = methods;
    }

    public void setClassNames(ArrayList<String> classNames) {
        this.classNames = classNames;
    }

    public void setProviders(String[] providers) {
        this.providers = providers;
    }

    public void setClients(String[] clients) {
        this.clients = clients;
    }



    public void printProviders(){
        ArrayList<String> temp = getClassNames();
        System.out.print("\nProviders: \n");

        if(temp!=null){
            for(String s: temp){
                System.out.println("    "+s);
            }
        }
        else{
            System.out.print("No classes declared.");
        }

    }

    public void printFields(){
        System.out.print("\nFields: ");
        String[] s = getFields();
        for(String m: s){
            System.out.print("\n    "+m);
        }
        //System.out.print("\n    ..... .\n");
    }

    public void printMethods(){
        System.out.print("\nMethods: ");
        String[] s = getMethods();
        for(String m: s){
            System.out.print("\n    "+m);
        }
        //System.out.print("\n    ..... .\n");
    }

    public void printInterfaces(){
        System.out.print("\nInterfaces: ");
        String[] interfaces = getInterfaces();
        if(interfaces!=null){
            for(String s: interfaces){
                System.out.println("    "+s);
            }
        }
        else if(interfaces.length==0||interfaces.equals("Object")){
            System.out.print("None");
        }

    }

    public void printSuperclass(){
        System.out.print("\nSuperclass: ");
        if(getSuperClassName().equals("java.lang.Object")){
            System.out.print("None");
        }
        else{
            System.out.print(getSuperClassName());
        }
    }

    public void printClass(){
        System.out.print("\nClass Details: ");
        System.out.print("\nName: "+getName());
        printSuperclass();
        printInterfaces();
        printFields();
        printMethods();
        printProviders();

    }

    @Override
    public String toString(){
        String result = getName()+"Fields: ";
        return result;
    }
}
