package com.netease.fileupload.service.organ;

import java.util.Map;

/**
 * Created by goforit on 2017/9/15.
 */
public interface OrganizationService {

    void resolveOrgan(String path) throws OrganizationException;

    void resolveOrgan(Map<String, Organization> organizationMap, String path, String session) throws OrganizationException;

}
