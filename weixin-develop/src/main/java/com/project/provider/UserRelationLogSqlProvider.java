package com.project.provider;

import com.project.common.util.LogUtil;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import org.apache.commons.logging.Log;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by goforit on 2017/12/3.
 */
public class UserRelationLogSqlProvider {

    private Log logger = LogUtil.getLogger(UserSqlProvider.class);

    /**
     * 插入语句
     *
     * @param userRelation
     * @return
     */
    public String insert(final UserRelation userRelation) {
        return new SQL() {{
            INSERT_INTO("User_Relation_Log");
            if (userRelation.getIntroducer() != null) {
                VALUES("introducer", "#{introducer}");
                VALUES("introducername", "#{introducername}");
            }
            if (userRelation.getNewmember() != null) {
                VALUES("newmember", "#{newmember}");
                VALUES("newmembername", "#{newmembername}");
            }
            VALUES("releation", "#{releation}");
            VALUES("lmodify", "#{lmodify}");
        }}.toString();

    }


    /**
     * 根据添加选择
     *
     * @param page
     * @return
     */
    public String selectList(Page page, UserRelation userRelation) {
        return new SQL() {{
            SELECT("newmember, newmembername, releation, introducer, introducername, lmodify");
            FROM("User_Relation");
            if (userRelation.getNewmember() != null) {
                WHERE("newmember = #{newmember}");
            }
            if (userRelation.getNewmembername() != null) {
                WHERE("newmembername = #{newmembername}");
            }
            if (userRelation.getIntroducer() != null) {
                WHERE("introducer = #{introducer}");
            }
            if (userRelation.getIntroducername() != null) {
                WHERE("introducername = #{introducername}");
            }
            ORDER_BY("lmodify desc");
        }}.toString();
    }

    /**
     *展示列表
     * @return
     */
    public String selectListNew(){
        return new SQL(){{
            SELECT("introducer, newmember, releation, lmodify, introducername, newmembername");
            FROM("User_Relation_Log");
            ORDER_BY("lmodify desc");
        }}.toString();
    }

    /**
     * 更新
     * @param userRelation
     * @return
     */
    public String update(final UserRelation userRelation) {
        return new SQL() {{
            UPDATE("User_Relation_Log");
            if (userRelation.getIntroducer() != null) {
                SET("introducer=#{introducer}");
            }
            if (userRelation.getIntroducername() != null) {
                SET("introducername=#{introducername}");
            }
            if (userRelation.getNewmember() != null) {
                SET("newmember=#{newmember}");
            }
            if (userRelation.getNewmembername() != null) {
                SET("newmembername=#{newmembername}");
            }
            if (userRelation.getLmodify() != null) {
                SET("lmodify=#{lmodify}");
            }
            if (userRelation.getReleation() != 0) {
                SET("releation=#{releation}");
            }

            WHERE("id=#{id}");

        }}.toString();

    }

}
