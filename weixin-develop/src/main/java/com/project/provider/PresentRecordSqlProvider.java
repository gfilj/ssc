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
     * @param presentRecord
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
    public String update(final PresentRecord presentRecord) {
        return new SQL() {
            {
                UPDATE("presentrecord");
                if (presentRecord.getMoneyTime() != null) {
                    SET("moneyTime=#{moneyTime}");
                }
                if (presentRecord.getInsertTime() != null) {
                    SET("insertTime=#{insertTime}");
                }
                if (presentRecord.getOperator() != null) {
                    SET("operator=#{operator}");
                }
                if (presentRecord.getMoney() != 0) {
                    SET("money=#{money}");
                }

                WHERE("id=#{id}");
            }
        }.toString();
    }

}
