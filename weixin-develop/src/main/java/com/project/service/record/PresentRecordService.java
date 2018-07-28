package com.project.service.record;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.sql.PresentRecord;
import com.project.model.sql.SystemUser;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.BufferOverflowException;
import java.util.Date;
import java.util.List;

import static com.project.common.util.LogUtil.logstr;

/**
 * Created by goforit on 2018/5/22.
 */
@Service
public class PresentRecordService {
    @Autowired
    private PresentRecordDBService presentRecordDBService;
    private Log logger = LogUtil.getLogger(getClass());

    public void add(PresentRecord presentRecord, SystemUser systemUser) throws BusinessException {
        presentRecord.setInsertTime(new Date());
        presentRecord.setOperator(systemUser.getUsername());
        presentRecordDBService.insert(presentRecord);
    }

    public PageInfo<PresentRecord> list(Page page) throws BusinessException {

        String funcname = "提现记录列表";
        PageHelper.offsetPage(page.getOffset(),page.getSize());
        logger.info(logstr(funcname,"偏移量",page.getOffset(),"页面大小",page.getSize()));
        List<PresentRecord> presentRecords = presentRecordDBService.selectList();
        int i=1;
        for(PresentRecord presentRecord:presentRecords){
            presentRecord.setId(page.getOffset()+i);
            i++;
        }
        return new PageInfo<PresentRecord>(presentRecords);
    }

    public void update(PresentRecord presentRecord, SystemUser systemUser) throws BusinessException {
        presentRecord.setInsertTime(new Date());
        presentRecord.setOperator(systemUser.getUsername());
        presentRecordDBService.update(presentRecord);
    }
}
