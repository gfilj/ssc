package com.project.mapper;

import com.project.model.sql.PresentRecord;
import com.project.provider.PresentRecordSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by goforit on 2018/5/22.
 */
@Mapper
public interface PresentRecordMapper {
    @InsertProvider(type = PresentRecordSqlProvider.class, method = "insert")
    int insert(PresentRecord presentRecord) throws DataAccessException;

    @SelectProvider(type = PresentRecordSqlProvider.class, method = "selectList")
    List<PresentRecord> selectList() throws DataAccessException;

    @UpdateProvider(type = PresentRecordSqlProvider.class, method = "update")
    int update(PresentRecord presentRecord) throws DataAccessException;
}
