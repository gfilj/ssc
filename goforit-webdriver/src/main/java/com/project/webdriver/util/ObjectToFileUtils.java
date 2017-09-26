package com.project.webdriver.util;

import com.project.webdriver.model.User;
import org.apache.commons.collections.map.HashedMap;

import java.io.*;
import java.util.Map;

/**
 * Created by goforit on 2017/9/5.
 */
public class ObjectToFileUtils {

    private static final String Path_Cache="cacheLocalValue.txt";

    public static void writeObject(Map<String,User> cacheBean) {
        FileOutputStream outStream=null;
        try {

            if(null==cacheBean){
                return;
            }
            outStream = new FileOutputStream(Path_Cache);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);

            objectOutputStream.writeObject(cacheBean);
            outStream.close();

            System.out.println(" cache  ----- successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null!=outStream){
                    outStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static  Map<String,User> readObject(){
        FileInputStream freader = null;
        try {

            File file=new File(Path_Cache);
            if(!file.exists()){
                System.out.println("--------------------->  没有缓存文件");
                return new HashedMap();
            }

            freader = new FileInputStream(Path_Cache);
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            Map<String,User> bean = (Map<String,User>) objectInputStream.readObject();

            return bean;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(null!=freader){
                    freader.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;

    }
}
