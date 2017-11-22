package danil;

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

    public void setProviders(String[] providers) {
        this.providers = providers;
    }

    public void setClients(String[] clients) {
        this.clients = clients;
    }



    public void printFields(){
        String[] s = getFields();
        for(String m: s){
            System.out.print("\n    "+m);
        }
        System.out.print("\n    ..... .\n");
    }

    public void printMethods(){
        String[] s = getMethods();
        for(String m: s){
            System.out.print("\n    "+m);
        }
        System.out.print("\n    ..... .\n");
    }

    public void printInterfaces(){
        String[] interfaces = getInterfaces();
        if(interfaces!=null){
            for(String s: interfaces){
                System.out.print(s);
            }
        }
        else{
            System.out.print("Nothing");
        }

    }

    public void printSuperclass(){
        if(getSuperClassName().equals("Object")){
            System.out.print("Nothing");
        }
        else{
            System.out.print("\n"+getSuperClassName());
        }
    }

    public void printClass(){
        System.out.print("\nClass Details: ");
        System.out.print("\nName: "+getName());
        System.out.print("\nSuperclass: ");
        printSuperclass();
        System.out.print("\nInterfaces: ");
        printInterfaces();
        System.out.print("\nFields: ");
        printFields();
        System.out.print("\nMethods: ");
        printMethods();

    }

    @Override
    public String toString(){
        String result = getName()+"Fields: ";
        return result;
    }
}
