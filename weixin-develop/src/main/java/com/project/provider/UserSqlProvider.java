package com.project.provider;

import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;

/**
 * Created by goforit on 2017/12/2.
 */
public class UserSqlProvider {

    private Logger logger = LogUtil.getLogger(UserSqlProvider.class);

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
}
