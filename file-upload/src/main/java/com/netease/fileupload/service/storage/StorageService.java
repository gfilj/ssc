package com.netease.fileupload.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init() throws StorageException;

    String store(MultipartFile file) throws StorageException;

    String store(MultipartFile file, String filename, String path) throws StorageException;

    Stream<Path> loadAll() throws StorageException;

    Path load(String filename);

    Resource loadAsResource(String filename) throws StorageException;

    void deleteAll();

    void delete(String filename) throws StorageException;

}