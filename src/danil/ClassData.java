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
    private ArrayList<String> providers;
    private ArrayList<String> clients;

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

    public ArrayList<String> getProviders() {
        return providers;
    }

    public ArrayList<String> getClients() {
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

    public void setProviders(ArrayList<String> providers) {
        this.providers = providers;
    }

    public void setClients(ArrayList<String> clients) {
        this.clients = clients;
    }


    public void printProviders(){
        ArrayList<String> temp = getProviders();
        System.out.print("\nProviders: \n");

        if(temp!=null){
            for(String s: temp){
                System.out.println("    "+s);
            }
        }
        else{
            System.out.print("No providers.");
        }

    }

    public void printClients(){
        ArrayList<String> temp = getClients();
        System.out.print("Clients: \n");

        if(temp!=null){
            for(String s: temp){
                System.out.println("    "+s);
            }
        }
        else{
            System.out.print("No clients.");
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
                System.out.print(""+s);
            }
        }
        else if(interfaces.length==0||interfaces.equals("Object")){
            System.out.print("None");
        }

    }

    public void printSuperclass() throws NullPointerException{
        System.out.print("\nSuperclass: ");
        if(getSuperClassName() == null){
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
        printClients();

    }

    @Override
    public String toString(){
        String result = getName()+"Fields: ";
        return result;
    }
}
