package com.project.provider;

import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;

/**
 * Created by goforit on 2017/12/2.
 */
public class UserSqlProvider {

    private Logger logger = LogUtil.getLogger(UserSqlProvider.class);

    /**
     * 插入语句
     * @param user
     * @return
     */
    public String insert(final User user){
        String sql = new SQL() {{

            INSERT_INTO("User");
            if (user.getOpenid() != null) {
                VALUES("openid", "#{openid}");
            }
            VALUES("subscribe", "#{subscribe}");
            VALUES("nickname", "#{nickname}");
            VALUES("sex", "#{sex}");
            VALUES("language", "#{language}");
            VALUES("city", "#{city}");
            VALUES("province", "#{province}");
            VALUES("country", "#{country}");
            VALUES("headimgurl", "#{headimgurl}");
            VALUES("subscribe_time", "#{subscribe_time}");
            VALUES("remark", "#{remark}");
        }}.toString();
        sql = sql.replace("INSERT","REPLACE");
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
            FROM("User");
        }}.toString();
    }

    /**
     *
     * @param page
     * @return
     */
    public String selectPageList(Page page){
        return new SQL(){{
            SELECT("openid, nickname, sex, city, province, country, subscribe_time");
            FROM("User");
            ORDER_BY("subscribe_time desc limit #{start},#{end}");
        }}.toString();
    }


}
