package com.netease.fileupload.service.storage;

import com.netease.fileupload.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import static com.netease.fileupload.service.storage.StorageExceptionEnum.*;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    private Logger logger = LogUtil.getLogger(getClass());

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
        File file = this.rootLocation.toFile();
        if(!file.exists()){
            file.mkdirs();
        }
    }

    @Override
    public String store(MultipartFile file) throws StorageException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        return store(file, filename,"/");
    }

    @Override
    public String store(MultipartFile file, String filename, String path) throws StorageException {

        try {
            if(StringUtils.isEmpty(filename)){
                filename = StringUtils.cleanPath(file.getOriginalFilename());
            }
            filename = pathResolve(filename, path);
            if (file.isEmpty()) {
                throw new StorageException(EMPTY_CAUSE.setFilename(filename));
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(SECURITY_CAUSE.setFilename(filename));
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new StorageException(IOEXCEPTION_CAUSE.setFilename(filename), e);
        }
    }

    private String pathResolve(String filename, String path) {
        if(!StringUtils.isEmpty(path)||!"/".equals(path)){
            File dirFile = rootLocation.resolve(path).toFile();
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            filename=path+filename;
        }
        return filename;
    }

    @Override
    public Stream<Path> loadAll() throws StorageException {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException(READ_CAUSE, e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws StorageException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException(FILE_NOT_FOUND_CAUSE.setFilename(filename));

            }
        } catch (MalformedURLException e) {
            throw new StorageException(FILE_NOT_FOUND_CAUSE.setFilename(filename), e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void delete(String filename) throws StorageException {
        try {
            loadAsResource(filename).getFile().delete();
        } catch (IOException e) {
            throw new StorageException(IOEXCEPTION_CAUSE, e);
        }
    }


    @Override
    public void init() throws StorageException {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException(INIT_CAUSE, e);
        }
    }
}
