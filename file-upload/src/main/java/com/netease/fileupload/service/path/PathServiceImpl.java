package com.netease.fileupload.service.path;

import com.netease.fileupload.service.organ.Organization;
import com.netease.fileupload.util.MD5Util;
import com.netease.fileupload.util.PropertyLoad;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by goforit on 2017/9/12.
 */
@Service
public class PathServiceImpl implements PathService {

    public static final String split_char = PropertyLoad.getProperty("split_char");
    public static final String upload_api = PropertyLoad.getProperty("upload_api");
    public static final String upload_api_generate = PropertyLoad.getProperty("upload_api_generate");


    public String resolve(String path) {
        return path.replaceAll(split_char, File.separator);
    }

    public Map<String, Organization> resolveToOrganization(String path) {
        Map<String, Organization> organizationHashMap = new HashMap<>();
        Organization lastNode = new Organization();
        boolean isRootCode = true;
        for (String node : organNodeArr(path)) {
            if (StringUtils.isEmpty(node)) {
                continue;
            }
            Organization organization = new Organization();
            organization.setName(node);
            organization.setParentCode(lastNode.getOrgCode());

            String orgCode = lastNode.getOrgCodeBeforeMd5() + node + split_char;
            organization.setOrgCodeBeforeMd5(orgCode);
            if(isRootCode){
                organization.setOrgCode(node);
                isRootCode = false;
            }else{
                organization.setOrgCode(MD5Util.stringMD5(orgCode));
            }

            lastNode = organization;
            organizationHashMap.put(node, organization);
        }
        return organizationHashMap;
    }

    @Override
    public String[] organNodeArr(String path) {
        return path.split(split_char);
    }

    @Override
    public String getOrganStr(String servletPath) {
        Pattern pattern = Pattern.compile(upload_api);
        Matcher matcher = pattern.matcher(servletPath);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    @Override
    public String turnIntoOrgCode(String path) {
        if(!StringUtils.isEmpty(path)){
            String organ = getOrganStr(path);
            String orgCode = getOrgCode(organ);
            return String.format(upload_api_generate,orgCode);
        }else{
            return path;
        }
    }

    private String getOrgCode(String organ) {
        Map<String, Organization> organizationMap = resolveToOrganization(organ);
        String[] organNodeArr = organNodeArr(organ);
        String lastNode = organNodeArr[organNodeArr.length-1];
        return organizationMap.get(lastNode).getOrgCode();
    }


}
