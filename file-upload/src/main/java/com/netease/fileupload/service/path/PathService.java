package com.netease.fileupload.service.path;

import com.netease.fileupload.service.organ.Organization;

import java.util.Map;

/**
 * Created by goforit on 2017/9/12.
 */
public interface PathService {

    /**
     * 路径处理
     * @param path
     * @return
     */
    String resolve(String path);

    /**
     * 处理后的组织机构
     * @param path
     * @return
     */
    Map<String, Organization> resolveToOrganization(String path);

    /**
     * 返回org分割字符串
     * @param path
     * @return
     */
    String[] organNodeArr(String path);

    /**
     * 提取org字符串
     * @param servletPath
     * @return
     */
    String getOrganStr(String servletPath);

    /**
     * 路径和org-code 转换
     * @param path
     * @return
     */
    String turnIntoOrgCode(String path);
}
