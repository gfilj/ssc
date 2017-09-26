package com.project.service;

import com.project.spider.mapper.BiBoHuiAccountMapper;
import com.project.spider.model.Row;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/8/30.
 */
@Service
public class BiBoHuiAccountService {

    private static Logger log = LoggerFactory.getLogger(BiBoHuiAccountService.class);

    @Autowired
    private BiBoHuiAccountMapper biBoHuiAccountMapper;

    public int insertSelective(Row row){
        try{
            row.put("end_time",DateTime.now().plusDays(row.getInt("end_time")).toDate());
            biBoHuiAccountMapper.insertSelective(row);
        }catch (Exception e){
            log.error(row.toString(),e);
        }
        return 0;
    }



}
