package com.project.storage;

import com.alibaba.fastjson.JSONObject;
import com.project.FileUploadController;
import com.project.common.result.ResultBuilder;
import com.project.common.util.LogUtil;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by goforit on 2018/4/8.
 */
@Controller
@RequestMapping("/stream")
public class StreamUploadController {
    Log logger = LogUtil.getLogger(getClass());

    public String IMGSERVER = "";
    @Autowired
    private StorageService storageService;
    @RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=utf-8", value = "/upload")
    public @ResponseBody JSONObject handleFileUpload(@RequestParam("file")MultipartFile file) {
        logger.info(file.getOriginalFilename());
        storageService.store(file);
        JSONObject jo = new JSONObject();
        jo.put("errno", 0);
        jo.put("url", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                "serveInlineFile", storageService.load(file.getOriginalFilename()).getFileName().toString()).build().toString());
        jo.put("server", IMGSERVER );
        jo.put("errMsg", "上传成功");
        return jo;
    }

}
