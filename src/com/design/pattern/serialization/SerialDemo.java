package com.design.pattern.serialization;

import java.io.*;

public class SerialDemo {


    public static void main(String[] args) throws Exception {

        ObjSer obj = new ObjSer();
        obj.i = 1;
        File file = new File("obj.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream ostr = new ObjectOutputStream(fos);
        ostr.writeObject(obj);


        ostr.close();
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        //ObjectInputStream.GetField fie = ois.readFields();
        ObjSer x = (ObjSer) ois.readObject();
        System.out.println(x);
    }

}

class ObjSer implements Serializable{
    public int i;
}