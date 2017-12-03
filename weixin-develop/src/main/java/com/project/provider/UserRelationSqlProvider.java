package com.project.provider;

import com.project.common.util.LogUtil;
import com.project.model.sql.UserRelation;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;

/**
 * Created by goforit on 2017/12/3.
 */
public class UserRelationSqlProvider {

    private Logger logger = LogUtil.getLogger(UserSqlProvider.class);

    public String insert(final UserRelation userRelation){
        String sql = new SQL() {{

            INSERT_INTO("User_Relation");
            if (userRelation.getIntroducer() != null) {
                VALUES("introducer", "#{introducer}");
                VALUES("introducername", "#{introducername}");
            }
            if (userRelation.getNewmember() !=null){
                VALUES("newmember", "#{newmember}");
                VALUES("newmembername", "#{newmembername}");
            }
            VALUES("releation", "#{releation}");
            VALUES("lmodify", "#{lmodify}");
        }}.toString();
        logger.info(sql);
        return sql;
    }
}
