package com.project.common.storage;

import com.project.common.util.LogUtil;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * 文件存储基类
 * Created by goforit on 2017/12/23.
 */
public class LocalStorage<T> {

    private Logger logger = LogUtil.getLogger(getClass());

    private String path;

    public String getPath() {
        return path;
    }

    /**
     * 设置path
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 存储到本地
     * @param t
     */
    public void write(T t) {
        FileOutputStream outStream=null;
        try {
            if(null==t){
                return;
            }
            outStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);

            objectOutputStream.writeObject(t);
            outStream.close();

            logger.info(String.format("存储到本地成功，路径为:" + path));
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
               logger.error("持久化到本地错误",e);
            }
        }
    }

    public T read(){
        FileInputStream freader = null;
        try {

            File file=new File(path);
            if(!file.exists()){
                logger.info("没有相关的存储文件，路径为：" + path);
               return null;
            }

            freader = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            T bean = (T) objectInputStream.readObject();
            logger.info("获取存储文件成功，路径为：" + path);
            return bean;

        } catch (FileNotFoundException e) {
            logger.error("文件没找到,路径为："+path,e);
        } catch (IOException e) {
            logger.error("IO异常,路径为：" + path,e);
        } catch (ClassNotFoundException e) {
            logger.error("强制转换类型错误,路径为：" + path,e);
        }finally{
            try {
                if(null!=freader){
                    freader.close();
                }
            } catch (IOException e) {
                logger.error("释放reader时IO异常,路径为：" + path,e);
            }
        }
        return null;
    }

}
