package com.netease.fileupload.controller;

import com.netease.fileupload.model.Result;
import com.netease.fileupload.model.User;
import com.netease.fileupload.model.builder.ResultBuilder;
import com.netease.fileupload.service.MD5.MD5Exception;
import com.netease.fileupload.service.auth.AuthService;
import com.netease.fileupload.service.organ.OrganizationService;
import com.netease.fileupload.service.path.PathService;
import com.netease.fileupload.service.upload.UploadException;
import com.netease.fileupload.service.upload.UploadExceptionEnum;
import com.netease.fileupload.service.upload.UploadService;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by goforit on 2017/9/1.
 */
@Controller
@RequestMapping("/fileupload")
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @Autowired
    private PathService pathService;
    @Autowired
    private OrganizationService organizationService;

    private Logger logger = LogUtil.getLogger(getClass());

    @RequestMapping(method = RequestMethod.POST,produces="application/json;charset=utf-8", value = "{path:.+}/add")
    public @ResponseBody
    Result add(HttpServletRequest request, @PathVariable String path, @RequestParam("file") MultipartFile file, @RequestParam("md5") String md5, @RequestParam("filename") String filename) {
        try {
            path=pathService.resolve(path);
            filename = uploadService.store(file,filename,path);
            uploadService.check(filename,md5);
            return ResultBuilder.getInstance(UploadExceptionEnum.SUCCESS.getResult()).setData(path).build();

        } catch (UploadException e) {
            logger.error(Constant.ERRORStr(filename+ " UploadException"),e);
            uploadService.deleteFile(filename);
            return e.getUploadExceptionEnum().getResult();

        } catch (MD5Exception e) {
            logger.error(Constant.ERRORStr(filename+ " MD5Exception"),e);
            uploadService.deleteFile(filename);
            return e.getMd5ExceptionEnum().getResult();

        }
    }

    @Autowired
    private AuthService authService;
    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    public @ResponseBody
    Result auth(@RequestBody User user) {
        return authService.auth(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/alive")
    public @ResponseBody
    String auth() {
        return "";
    }
}
