package com.project.service.record;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.PresentRecordMapper;
import com.project.model.sql.PresentRecord;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by goforit on 2018/5/22.
 */
@Service
public class PresentRecordDBService {
    @Autowired
    private PresentRecordMapper presentRecordMapper;
    Log logger = LogUtil.getLogger(getClass());
    private String className = "提现记录";

    public int insert(PresentRecord presentRecord) throws BusinessException {
        try{
            return presentRecordMapper.insert(presentRecord);
        }catch (Throwable e){
            logger.error(LogUtil.logstr(className+"/插入报错","message",e.getMessage()),e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE,e);
        }
    }

    public List<PresentRecord> selectList() throws BusinessException {
        try{
            return presentRecordMapper.selectList();
        }catch (Throwable e){
            logger.error(LogUtil.logstr(className+"/查询报错","message",e.getMessage()));
            throw new BusinessException(ExceptionEnum.DATA_CAUSE,e);
        }
    }
}
