package com.project.provider;

import com.project.common.sql.SqlUtil;
import com.project.common.util.LogUtil;
import com.project.model.sql.PresentRecord;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import org.apache.commons.logging.Log;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by goforit on 2017/12/2.
 */
public class PresentRecordSqlProvider {

    private Log logger = LogUtil.getLogger(PresentRecordSqlProvider.class);

    /**
     * 插入语句
     *
     * @param user
     * @return
     */
    public String insert(final PresentRecord presentRecord) {
        String sql = new SQL() {{
            INSERT_INTO("presentrecord");
            VALUES("money", "#{money}");
            VALUES("moneyTime", "#{moneyTime}");
            VALUES("insertTime", "#{insertTime}");
            VALUES("operator", "#{operator}");
        }}.toString();
        sql = SqlUtil.relaceInto(sql);
        logger.info(sql);
        return sql;
    }


    /**
     * 分页语句 PageHelper
     *
     * @return
     */
    public String selectList() {
        return new SQL() {{
            SELECT("*");
            FROM("presentrecord");
            ORDER_BY("insertTime desc");
        }}.toString();
    }


    /**
     * 更新
     */
    public String update(User user) {
        return new SQL() {
            {
                UPDATE("User");
                if (user.getQrCode() != null) {
                    SET("qrCode=#{qrCode}");
                }
                WHERE("openid=#{openid}");
            }
        }.toString();
    }

}
