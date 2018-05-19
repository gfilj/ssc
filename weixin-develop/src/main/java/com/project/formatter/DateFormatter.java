package com.project.formatter;

import com.project.common.extension.SpringUtil;
import com.project.common.util.LogUtil;
import org.apache.commons.logging.Log;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by goforit on 2018/5/16.
 */
public class DateFormatter implements Formatter<Date> {

    Log logger = LogUtil.getLogger(getClass());
    @Override
    public String print(Date object, Locale locale) {
        return null;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if (!StringUtils.isEmpty(text)) {
            try {
                Long datelong = Long.valueOf(text);
                date = new Date(datelong);
            } catch (Exception e) {
                try {
                    date = format.parse(text);
                } catch (Exception e1) {
                    try {
                        format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                        date = format.parse(text);
                    } catch (Exception e2) {
                        try {
                            format = new SimpleDateFormat("yyyy-MM-dd");
                            date = format.parse(text);
                        } catch (Exception e12) {
                            logger.error(LogUtil.logstr("日期类型准换","不支持",text));
                        }
                    }
                }
            }
        }
        return date;
    }

}
