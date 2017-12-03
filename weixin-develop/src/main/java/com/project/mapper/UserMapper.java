package com.project.mapper;

import com.project.model.sql.User;
import com.project.provider.UserSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

/**
 * Created by goforit on 2017/12/2.
 */
@Mapper
public interface UserMapper {

    @InsertProvider(type = UserSqlProvider.class, method = "insert")
    int insert(User user) throws DataAccessException;

}
