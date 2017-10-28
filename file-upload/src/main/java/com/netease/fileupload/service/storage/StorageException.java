package com.netease.fileupload.service.storage;

public class StorageException extends Throwable {

    private StorageExceptionEnum storageExceptionEnum;

    public StorageException(StorageExceptionEnum storageExceptionEnum) {
        super(storageExceptionEnum.getResult().getMsg());
        this.storageExceptionEnum = storageExceptionEnum;
    }

    public StorageException(StorageExceptionEnum storageExceptionEnum, Throwable cause) {

        super(storageExceptionEnum.getResult().getMsg(), cause);
        this.storageExceptionEnum = storageExceptionEnum;
    }

    public StorageExceptionEnum getStorageExceptionEnum() {
        return storageExceptionEnum;
    }
}
