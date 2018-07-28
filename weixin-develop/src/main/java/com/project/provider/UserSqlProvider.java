package com.project.provider;

import com.project.common.sql.SqlUtil;
import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import org.apache.commons.logging.Log;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by goforit on 2017/12/2.
 */
public class UserSqlProvider {

    private Log logger = LogUtil.getLogger(UserSqlProvider.class);

    /**
     * 插入语句
     *
     * @param user
     * @return
     */
    public String insert(final User user) {
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
            VALUES("qrCode", "#{qrCode}");
        }}.toString();
        sql = SqlUtil.relaceInto(sql);
        logger.info(sql);
        return sql;
    }

    /**
     * count语句
     *
     * @return
     */
    public String selectPageListCount() {
        return new SQL() {{
            SELECT("count(1)");
            FROM("User");
        }}.toString();
    }

    /**
     * 分页语句
     *
     * @param page
     * @return
     */
    public String selectPageList(Page page) {
        return new SQL() {{
            SELECT("openid, nickname, sex, city, province, country, subscribe_time");
            FROM("User");
            ORDER_BY("subscribe_time desc limit #{start},#{row}");
        }}.toString();
    }


    /**
     * 分页语句 PageHelper
     *
     * @return
     */
    public String selectList() {
        return new SQL() {{
            SELECT("*");
            FROM("wechat.User");
            ORDER_BY("subscribe_time desc");
        }}.toString();
    }

    /**
     * 搜索用户
     *
     * @return
     */
    public String selectOne(String openid) {
        return new SQL() {{
            SELECT("*");
            FROM("User");
            WHERE("openid=#{openid}");
        }}.toString();
    }

    /**
     * 更新
     */
    public String update(User user) {
        return new SQL() {
            {
                UPDATE("User");
                if (user.getSubscribe() != 0) {
                    SET("subscribe=#{subscribe}");
                }
                if (user.getQrCode() != null) {
                    SET("qrCode=#{qrCode}");
                }
                if (user.getNickname() != null) {
                    SET("nickname=#{nickname}");
                }
                if (user.getSex() != 0) {
                    SET("sex=#{sex}");
                }
                if (user.getLanguage() != null) {
                    SET("language=#{language}");
                }
                if (user.getCity() != null) {
                    SET("city=#{city}");
                }
                if (user.getProvince() != null) {
                    SET("province=#{province}");
                }
                if (user.getCountry() != null) {
                    SET("country=#{country}");
                }
                if (user.getHeadimgurl() != null) {
                    SET("headimgurl=#{headimgurl}");
                }
                if (user.getSubscribe_time() != null) {
                    SET("subscribe_time=#{subscribe_time}");
                }
                if (user.getRemark() != null) {
                    SET("remark=#{remark}");
                }
                if (user.getQrCode() != null) {
                    SET("qrCode=#{qrCode}");
                }
                if (user.getQrCode1() != null) {
                    SET("qrCode1=#{qrCode1}");
                }
                if (user.getTel() != null) {
                    SET("tel=#{tel}");
                }
                if (user.getName() != null) {
                    SET("name=#{name}");
                }
                if (user.getIdentity() != null) {
                    SET("identity=#{identity}");
                }
                if (user.getIdentitytime() != null) {
                    SET("identitytime=#{identitytime}");
                }
                if (user.getHighcode() != null) {
                    SET("highcode=#{highcode}");
                }
                if (user.getSupercode() != null) {
                    SET("supercode=#{supercode}");
                }
                if (user.getFirstbuycode() != null) {
                    SET("firstbuycode=#{firstbuycode}");
                }
                if (user.getFirstbuycodeuse() != null) {
                    SET("firstbuycodeuse=#{firstbuycodeuse}");
                }
                if (user.getWechat() != null) {
                    SET("wechat=#{wechat}");
                }

                WHERE("openid=#{openid}");
            }
        }.toString();
    }


    /**
     * 查询
     */
    public String search(Map<String,Object> map) {
        String searchUser = new SQL() {
            {
                SELECT("*");
                FROM("User");
                for (String key :
                        map.keySet()) {
                    WHERE(key + " like #{" + key + "}");
                }
            }
        }.toString();
        logger.info(LogUtil.logstr("模糊查询语句：" ,"sql",searchUser));
        return searchUser;
    }

    /**
     * 根据openid获取订单
     * @param openid
     * @return
     */
    public String getOrderByOpenid(String openid) {
        return new SQL() {{
            SELECT("a.no, a.submitOrderTime, a.paySuccessTime, " +
                    "a.realname, a.tel, a.mark, a.address, a.productnumber, " +
                    "a.productname, a.quantity, a.jingdname");
            FROM("wechat.Order a, User b");
            WHERE("b.openid=#{openid} and b.tel=a.tel");
            ORDER_BY("a.paySuccessTime desc");
        }}.toString();
    }

}
