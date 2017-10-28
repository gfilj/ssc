package com.netease.fileupload.service.upload;

import com.netease.fileupload.service.MD5.MD5Exception;
import com.netease.fileupload.service.MD5.MD5Service;
import com.netease.fileupload.service.storage.StorageException;
import com.netease.fileupload.service.storage.StorageService;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by goforit on 2017/9/11.
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private MD5Service md5Service;
    @Autowired
    private StorageService storageService;


    private Logger logger = LogUtil.getLogger(getClass());

    @Override
    public void check(MultipartFile file, String md5) throws UploadException {
        try {
            md5Service.check(file.getBytes(), md5);
        } catch (IOException e) {
            throw new UploadException(UploadExceptionEnum.IOEXCEPTION_CAUSE, e);
        } catch (MD5Exception e) {
            throw new UploadException(e.getMd5ExceptionEnum().getResult());
        }
    }

    @Override
    public void check(String filename, String md5) throws MD5Exception {

            Path path = storageService.load(filename);
            md5Service.check(path.toString(), md5);

    }

    @Override
    public String store(MultipartFile file, String filename, String path) throws UploadException {
        try {
            return storageService.store(file, filename, path);
        } catch (StorageException e) {
            throw new UploadException(e.getStorageExceptionEnum().getResult());
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            storageService.delete(filename);
        } catch (StorageException e) {
            logger.info(Constant.ERRORStr(String.format("deleteFile %s error", filename)), e);
        }
    }


}
