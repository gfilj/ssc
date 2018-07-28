package com.project.mapper;

import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.provider.UserRelationSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by goforit on 2017/12/3.
 */
@Mapper
public interface UserRelationMapper {

    @InsertProvider(type = UserRelationSqlProvider.class, method = "insert")
    int insert(UserRelation userRelation) throws DataAccessException;

    @SelectProvider(type = UserRelationSqlProvider.class, method = "selectPageList")
    List<UserRelation> selectPageList(Page page) throws DataAccessException;

    @SelectProvider(type = UserRelationSqlProvider.class, method = "selectPageListCount")
    int selectPageListCount() throws DataAccessException;

    @SelectProvider(type = UserRelationSqlProvider.class, method = "selectList")
    List<UserRelation> selectList() throws DataAccessException;

    @UpdateProvider(type = UserRelationSqlProvider.class, method = "update")
    int update(UserRelation userRelation) throws DataAccessException;
}
