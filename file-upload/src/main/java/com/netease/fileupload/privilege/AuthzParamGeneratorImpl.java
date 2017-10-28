package com.netease.fileupload.privilege;

import com.netease.common.privilege.client.base.AuthParam;
import com.netease.common.privilege.client.base.NetUtil;
import com.netease.common.privilege.client.base.generator.AuthParamGenerator;
import com.netease.common.privilege.client.conf.ConfigLoader;
import com.netease.fileupload.model.Row;
import com.netease.fileupload.service.organ.Organization;
import com.netease.fileupload.service.organ.OrganizationException;
import com.netease.fileupload.service.organ.OrganizationServiceImpl;
import com.netease.fileupload.service.path.PathServiceImpl;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import com.netease.fileupload.util.PropertyLoad;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;

/**
 * Created by goforit on 2017/9/11.
 */
public class AuthzParamGeneratorImpl implements AuthParamGenerator {
    private static final String HTTP_SCHEMA = ConfigLoader.loader("http_schema");
    private static final String AUTHZ_SERVER_HOST = ConfigLoader.loader("authz_server_host");
    private static final int AUTHZ_SERVER_HOST_PORT = Integer.valueOf(ConfigLoader.loader("authz_server_host_port")).intValue();

    private static String PROJECT_TOKEN = PropertyLoad.getProperty("project_token");
    private static String USERID_API = PropertyLoad.getProperty("userId_api");


    private Logger logger = LogUtil.getLogger(getClass());

    @Override
    public AuthParam generator(HttpServletRequest httpServletRequest) {
        String servletPath = httpServletRequest.getServletPath();
        PathServiceImpl pathService = new PathServiceImpl();
        String organStr = pathService.getOrganStr(servletPath);
        Map<String, Organization> organizations = pathService.resolveToOrganization(organStr);
        OrganizationServiceImpl organizationService = new OrganizationServiceImpl();
        organizationService.setPathService(pathService);
        String session = httpServletRequest.getParameter("session");
        try {
            organizationService.resolveOrgan(organizations, organStr, session);
        } catch (OrganizationException e) {
            e.printStackTrace();
        }
        AuthParam authParam = new AuthParam();
        authParam.setProjectToken(PROJECT_TOKEN);
        authParam.setWebRequest(httpServletRequest);
        Map<String, String> queryUserId = Row.getInstance()
                .put("session", session).getStringMap();

        int id = 0;
        try {
            URI uri = (new URIBuilder()).setScheme(HTTP_SCHEMA).setHost(AUTHZ_SERVER_HOST).setPort(AUTHZ_SERVER_HOST_PORT).setPath(USERID_API).build();
            String responseMessage = NetUtil.getHttpContent(uri.toString(), queryUserId, false, "utf-8", "utf-8");
            id = Integer.parseInt(responseMessage);
        } catch (Exception e) {
            logger.error(Constant.ERRORStr(session + " get user id error"));
        }

        authParam.setUserId(id);

        authParam.setUrl(pathService.turnIntoOrgCode(servletPath));
        logger.info(Constant.INFOStr("authParam ") + authParam.toString());
        return authParam;
    }


}
