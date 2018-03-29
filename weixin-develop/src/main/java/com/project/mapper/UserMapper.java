package com.project.mapper;

import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.provider.UserSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

/**
 * Created by goforit on 2017/12/2.
 */
@Mapper
public interface UserMapper {

    @InsertProvider(type = UserSqlProvider.class, method = "insert")
    int insert(User user) throws DataAccessException;

    @SelectProvider(type = UserSqlProvider.class, method = "selectPageList")
    List<User> selectPageList(Page page) throws DataAccessException;

    @SelectProvider(type = UserSqlProvider.class, method = "selectPageListCount")
    int selectPageListCount() throws DataAccessException;

    @SelectProvider(type = UserSqlProvider.class, method = "selectList")
    List<User> selectList() throws DataAccessException;

    @SelectProvider(type = UserSqlProvider.class, method = "selectOne")
    User selectOne(String openid) throws DataAccessException;

}
