package danil;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by danil on 11/22/17.
 */
public class Serialization {

    HashMap<String, ClassData> localMap = DataMap.getMap();

    public Serialization() {
    }

    public void serializeOneToXML(ClassData data) throws IOException {
        String name = data.getName()+".xml";
        FileOutputStream fos = new FileOutputStream(name);
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

    public void serializeMapToXML(String name) throws IOException {
        String fileName = name+".xml";
        FileOutputStream fos = new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            @Override
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :" + e.toString());
            }
        });
        encoder.writeObject(localMap);
        encoder.close();
        fos.close();
    }

    public void deserializeFromXML(String name) throws FileNotFoundException {
        String fileName = name+".xml";
        XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(fileName)));

        HashMap<String,ClassData> newMap = (HashMap) decoder.readObject();

//        for (Map.Entry<String, ClassData> entry : newMap.entrySet()) {
//            String key = entry.getKey();
//            System.out.print("Contents of the map: "+key);
//        }

        DataMap.setMap(newMap);
        decoder.close();

    }
}
