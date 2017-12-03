package com.project.mapper;

import com.project.model.sql.UserRelation;
import com.project.provider.UserRelationSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

/**
 * Created by goforit on 2017/12/3.
 */
@Mapper
public interface UserRelationMapper {

    @InsertProvider(type = UserRelationSqlProvider.class, method = "insert")
    int insert(UserRelation userRelation) throws DataAccessException;
}
