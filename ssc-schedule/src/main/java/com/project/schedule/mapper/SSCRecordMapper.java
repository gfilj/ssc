package com.project.schedule.mapper;

import com.project.schedule.model.SSCRecordRepo;
import com.project.spider.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by goforit on 2017/7/19.
 */
@Component
public interface SSCRecordMapper extends BaseMapper<SSCRecordRepo, Serializable> {

    @Override
    @Insert("innser into ")
    int insertSelective(SSCRecordRepo obj) throws DataAccessException;
}
