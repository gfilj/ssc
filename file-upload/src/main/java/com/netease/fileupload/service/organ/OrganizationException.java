package com.netease.fileupload.service.organ;

/**
 * Created by goforit on 2017/9/15.
 */
public class OrganizationException extends Throwable {

    private OrganizationExceptionEnum organizationExceptionEnum;

    public OrganizationException(OrganizationExceptionEnum organizationExceptionEnum) {
        super(organizationExceptionEnum.getResult().getMsg());
        this.organizationExceptionEnum = organizationExceptionEnum;
    }

    public OrganizationException(OrganizationExceptionEnum organizationExceptionEnum, Throwable cause) {

        super(organizationExceptionEnum.getResult().getMsg(), cause);
        this.organizationExceptionEnum = organizationExceptionEnum;
    }

    public OrganizationExceptionEnum getOrganizationExceptionEnum() {
        return organizationExceptionEnum;
    }
}
