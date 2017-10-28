package com.netease.fileupload.service.upload;

import com.netease.fileupload.service.MD5.MD5Exception;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by goforit on 2017/9/11.
 */
public interface UploadService {

    void check(MultipartFile file,String md5) throws UploadException;
    void check(String filename,String md5) throws UploadException, MD5Exception;

    String store(MultipartFile file, String filename, String path) throws UploadException;

    void deleteFile(String filename);
}
