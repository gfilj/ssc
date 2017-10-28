package com.netease.fileupload.service.organ;

import com.netease.common.privilege.client.base.http.ContentEntity;
import com.netease.common.privilege.client.base.util.AuthUtil;
import com.netease.fileupload.model.Row;
import com.netease.fileupload.service.path.PathService;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by goforit on 2017/9/15.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private PathService pathService;

    private Logger logger = LogUtil.getLogger(getClass());

    public PathService getPathService() {
        return pathService;
    }

    public void setPathService(PathService pathService) {
        this.pathService = pathService;
    }

    @Override
    public void resolveOrgan(String path) throws OrganizationException {

    }

    @Override
    public void resolveOrgan(Map<String, Organization> organizationMap, String path, String session) throws OrganizationException {
        ArrayList lastArrayList = new ArrayList();
        boolean isFirst = true;
        for (String nodeName : pathService.organNodeArr(path)) {
            ContentEntity contentEntity = null;
            try {
                contentEntity = queryOrgan(organizationMap, nodeName, session);
                if (contentEntity.getResultcode() == 200) {
                    ArrayList arrayList = (ArrayList) contentEntity.getData();
                    while (lastArrayList.size() != 0) {
                        LinkedHashMap map = (LinkedHashMap) lastArrayList.get(0);
                        if (nodeName.equals(String.valueOf(map.get("orgName")))) {
                            break;
                        }
                        lastArrayList.remove(map);
                    }
                    if (lastArrayList.size() == 0 && !isFirst) {
                        addOrgan(organizationMap, nodeName, session);
                    }
                    isFirst = false;
                    lastArrayList = arrayList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private ContentEntity queryOrgan(Map<String, Organization> organizationMap, String nodeName, String session) throws Exception {
        Organization organization = organizationMap.get(nodeName);
        Map<String, String> queryOrgan = Row.getInstance()
                .put("session", session)
                .put("projectToken", "56B1E580C3504552B61556B9DD002691")
                .put("parentCode", organization.getOrgCode()).getStringMap();
        ContentEntity contentEntity = AuthUtil.executeRequest(queryOrgan, "/admin/organ/tree/childrenhttp", false);
        logger.info(Constant.INFOStr("query Organ parentCode:" + organization.getOrgCodeBeforeMd5()) + contentEntity.toString());
        return contentEntity;

    }

    private void addOrgan(Map<String, Organization> organizationMap, String nodeName, String session) throws Exception {
        //插入
        Organization organization = organizationMap.get(nodeName);
        Map<String, String> addOrgan = Row.getInstance()
                .put("session", session)
                .put("projectToken", "56B1E580C3504552B61556B9DD002691")
                .put("orgCode", organization.getOrgCode())
                .put("parentCode", organization.getParentCode())
                .put("name", nodeName)
                .getStringMap();
        ContentEntity addhttp = AuthUtil.executeRequest(addOrgan, "/admin/organ/addhttp", false);
        logger.info(Constant.INFOStr("add Organ " + organization) + addhttp.toString());
    }
}
