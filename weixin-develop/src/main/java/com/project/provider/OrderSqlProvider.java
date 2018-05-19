package com.project.provider;

import com.project.common.sql.SqlUtil;
import com.project.common.util.LogUtil;
import com.project.model.order.OrderDetail;
import org.apache.commons.logging.Log;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 */
public class OrderSqlProvider {

    private Log logger = LogUtil.getLogger(OrderSqlProvider.class);

    /**
     * 插入语句
     * @param orderDetail
     * @return
     */
    public String insert(final OrderDetail orderDetail){
        String sql = new SQL() {{

            INSERT_INTO("wechat.Order");
            VALUES("no", "#{no}");
            VALUES("submitOrderTime", "#{submitOrderTime}");
            VALUES("paySuccessTime", "#{paySuccessTime}");
            VALUES("realname", "#{realname}");
            VALUES("tel", "#{tel}");
            VALUES("mark", "#{mark}");
            VALUES("address", "#{address}");
            VALUES("productnumber", "#{productnumber}");
            VALUES("productname", "#{productname}");
            VALUES("quantity", "#{quantity}");
            VALUES("jingdname", "#{jingdname}");
        }}.toString();
        sql = SqlUtil.relaceInto(sql);
        return sql;
    }

    /**
     * count语句
     * @return
     */
    public String selectPageListCount(){
        return new SQL(){{
            SELECT("count(1)");
            FROM("wechat.Order");
        }}.toString();
    }

    /**
     *分页语句 PageHelper
     * @return
     */
    public String selectList(){
        return new SQL() {{
            SELECT("no, submitOrderTime, paySuccessTime, " +
                    "realname, tel, mark, address, productnumber, " +
                    "productname, quantity, jingdname");
            FROM("wechat.Order");
            ORDER_BY("paySuccessTime desc");
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
}
