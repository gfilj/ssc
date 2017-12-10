package com.project.mapper;

import com.project.model.sql.User;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.provider.UserRelationLogSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by goforit on 2017/12/3.
 */
@Mapper
public interface UserRelationLogMapper {

    @InsertProvider(type = UserRelationLogSqlProvider.class, method = "insert")
    int insert(UserRelation userRelation) throws DataAccessException;

    @SelectProvider(type = UserRelationLogSqlProvider.class, method = "selectList")
    List<User> selectList(Page page, UserRelation userRelation) throws DataAccessException;

}
