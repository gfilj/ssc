package com.project.provider;

import com.project.common.sql.SqlUtil;
import com.project.common.util.LogUtil;
import com.project.model.sql.SystemUser;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import org.apache.commons.logging.Log;
import org.apache.ibatis.jdbc.SQL;

/**
 * Create by Fenix_Bao on 2018/4/9.
 */
public class SystemUserSqlProvider {

    private Log logger = LogUtil.getLogger(SystemUserSqlProvider.class);

    /**
     * 插入语句
     * @param systemUser
     * @return
     */
    public String insert(final SystemUser systemUser){
        String sql = new SQL() {{

            INSERT_INTO("system_user");
            VALUES("username", "#{username}");
            VALUES("password", "#{password}");
            VALUES("privilege", "#{privilege}");
        }}.toString();
        sql = SqlUtil.relaceInto(sql);
        logger.info(sql);
        return sql;
    }

    /**
     * count语句
     * @return
     */
    public String selectPageListCount(){
        return new SQL(){{
            SELECT("count(1)");
            FROM("system_user");
        }}.toString();
    }

    /**
     *分页语句
     * @param page
     * @return
     */
    public String selectPageList(Page page){
        return new SQL(){{
            SELECT("id, username, password, privilege");
            FROM("system_user");
            ORDER_BY("id desc limit #{start},#{row}");
        }}.toString();
    }


    /**
     *分页语句 PageHelper
     * @return
     */
    public String selectList(){
        return new SQL(){{
            SELECT("id, username, password, privilege");
            FROM("system_user");
            ORDER_BY("id desc");
        }}.toString();
    }

    /**
     * 搜索用户
     * @return
     */
    public String selectOne(String username){
        return new SQL(){{
            SELECT("id, username, password, privilege");
            FROM("system_user");
            WHERE("username=#{username}");
        }}.toString();
    }

    /**
     * 更新
     * @param systemUser
     * @return
     */
    public String update(final SystemUser systemUser){
        String sql = new SQL() {{
            UPDATE("system_user");
            if (systemUser.getUsername() != null) {
                SET("username=#{username}");
            }
            if (systemUser.getPassword() != null) {
                SET("password=#{password}");
            }
            if (systemUser.getPrivilege() != 0) {
                SET("privilege=#{privilege}");
            }

            WHERE("id=#{id}");
        }}.toString();
        logger.info(sql);
        return sql;
    }
}
