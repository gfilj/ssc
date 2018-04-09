package com.project.mapper;

import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.provider.SystemUserSqlProvider;
import com.project.provider.UserSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * $myDescription
 * Create by Fenix_Bao on 2018/4/9.
 */
@Mapper
public interface SystemUserMapper {
    @InsertProvider(type = SystemUserSqlProvider.class, method = "insert")
    int insert(User user) throws DataAccessException;

    @SelectProvider(type = SystemUserSqlProvider.class, method = "selectPageList")
    List<User> selectPageList(Page page) throws DataAccessException;

    @SelectProvider(type = SystemUserSqlProvider.class, method = "selectPageListCount")
    int selectPageListCount() throws DataAccessException;

    @SelectProvider(type = SystemUserSqlProvider.class, method = "selectList")
    List<User> selectList() throws DataAccessException;

    @SelectProvider(type = SystemUserSqlProvider.class, method = "selectOne")
    User selectOne(String openid) throws DataAccessException;
}
