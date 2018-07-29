package com.project.controller.distribution;

import com.github.pagehelper.PageInfo;
import com.project.common.exception.ArgumentException;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;
import com.project.common.util.ArgumentsUtil;
import com.project.common.util.LogUtil;
import com.project.model.sql.PresentRecord;
import com.project.model.sql.SystemUser;
import com.project.model.vo.Page;
import com.project.service.order.OrderService;
import com.project.service.record.PresentRecordService;
import com.project.webdriver.login.model.LoginModel;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by goforit on 2018/5/23.
 */
@Controller
@RequestMapping("/distribution/presentrecord")
public class PresentRecordController {
    @Autowired
    private PresentRecordService presentRecordService;

    private Log logger = LogUtil.getLogger(getClass());

    @RequestMapping(value = "add")
    @ResponseBody
    public Result add(PresentRecord presentRecord) {
        try {
            SystemUser systemUser = new SystemUser();
            systemUser.setUsername("");
            presentRecordService.add(presentRecord,systemUser);
            return ResultBuilder.getInstance().build(ExceptionEnum.PRESENT_RECORD_ADD, presentRecord);
        } catch (BusinessException e) {
            return e.getResult();
        }
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public Result list(Page page) {
        try {

            PageInfo<PresentRecord> list = presentRecordService.list(page);
            return ResultBuilder.getInstance().build(ExceptionEnum.PRESENT_RECORD_LIST, list);
        } catch (BusinessException e) {
            return e.getResult();
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Result update(PresentRecord presentRecord) {
        try {
            ArgumentsUtil.checkArgument(presentRecord.getId() == 0, ExceptionEnum.ARGUMENT_ILLEGAL_CAUSE,
                    null);
            SystemUser systemUser = new SystemUser();
            systemUser.setUsername("");
            presentRecordService.update(presentRecord,systemUser);
            return ResultBuilder.getInstance().build(ExceptionEnum.PRESENT_RECORD_ADD, presentRecord);
        } catch (BusinessException|ArgumentException e) {
            return e.getResult();
        }
    }

}
