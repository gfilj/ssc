package com.netease.fileupload.service.MD5;

/**
 * Created by goforit on 2017/9/11.
 */
public interface MD5Service {

    String fileMD5(String inputFile) throws MD5Exception;

    String byteArrMD5(byte[] fileByteArr) throws MD5Exception;

    String stringMD5(String input) throws MD5Exception;

    void check(byte[] fileByteArr,String md5) throws MD5Exception;

    void check(String filename, String md5) throws MD5Exception;
}
