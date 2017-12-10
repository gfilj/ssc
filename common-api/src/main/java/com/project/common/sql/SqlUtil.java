package com.project.common.sql;

/**
 * Created by goforit on 2017/12/10.
 */
public class SqlUtil {

    public static String relaceInto(String sql){
        return sql.replace("INSERT","REPLACE");
    }


}
