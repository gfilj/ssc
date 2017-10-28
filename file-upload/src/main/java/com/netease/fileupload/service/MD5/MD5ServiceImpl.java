package com.netease.fileupload.service.MD5;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.netease.fileupload.service.MD5.MD5ExceptionEnum.*;

/**
 * Created by goforit on 2017/9/11.
 */
@Service
public class MD5ServiceImpl implements MD5Service {


    @Override
    public String fileMD5(String inputFile) throws MD5Exception {
        // 缓冲区大小（这个可以抽出一个参数）
        int bufferSize = 1024 * 1024;
        FileInputStream fileInputStream = null;
        DigestInputStream digestInputStream = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用DigestInputStream
            fileInputStream = new FileInputStream(inputFile);
            digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
            // read的过程中进行MD5处理，直到读完文件
            byte[] buffer = new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0) ;

            // 获取最终的MessageDigest
            messageDigest = digestInputStream.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 同样，把字节数组转换成字符串

            return byteArrayToHex(resultByteArray);
        } catch (FileNotFoundException e) {
            throw new MD5Exception(FILE_NOT_FOUND_CAUSE, e);
        } catch (IOException e) {
            throw new MD5Exception(IOEXCEPTION_CAUSE, e);
        } catch (NoSuchAlgorithmException e) {
            throw new MD5Exception(NO_ALGORITHM_CAUSE, e);
        }
    }

    @Override
    public String byteArrMD5(byte[] fileByteArr) throws MD5Exception {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(fileByteArr);
            return byteArrayToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new MD5Exception(NO_ALGORITHM_CAUSE, e);
        }
    }


    @Override
    public String stringMD5(String input) throws MD5Exception {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            throw new MD5Exception(NO_ALGORITHM_CAUSE, e);
        }
    }

    @Override
    public void check(byte[] fileByteArr, String md5) throws MD5Exception {
        String inputMd5 = byteArrMD5(fileByteArr).toLowerCase();
        if (!inputMd5.equals(md5.toLowerCase())) {
            throw new MD5Exception(CHECK_ERROR_CAUSE);
        }
    }

    @Override
    public void check(String filename, String md5) throws MD5Exception {
        String inputMd5 = fileMD5(filename).toLowerCase();
        if (!inputMd5.equals(md5.toLowerCase())) {
            throw new MD5Exception(CHECK_ERROR_CAUSE);
        }
    }

    public String byteArrayToHex(byte[] byteArray) {

        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
