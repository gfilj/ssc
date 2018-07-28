package com.project.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by goforit on 2018/6/3.
 */
public class SwitchUtil {

    private static Log logger = LogUtil.getLogger(SwitchUtil.class);

    /**
     * 将map 转为Obj
     * @param map
     * @param beanClass
     * @return
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null)
            return null;
        Object obj = null;
        try {
            obj = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        setMapToObj(map, obj, fields);

        return obj;
    }

    /**
     * 将Obj转为map
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        setObjToMap(obj, map, declaredFields);

        return map;
    }


    /**
     * 将继承的所有属性转为Obj
     * @param map
     * @param beanClass
     * @return
     */
    public static Object allFieldMapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null)
            return null;
        Object obj = null;
        try {
            obj = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //Field[] declaredFields = obj.getClass().getDeclaredFields();
        Field[] declaredFields = ArrayUtils.addAll(obj.getClass().getDeclaredFields(), obj.getClass().getSuperclass().getDeclaredFields());

        setMapToObj(map, obj, declaredFields);
        return obj;
    }

    /**
     * 将obj从父类继承的所有属性转为Map
     * @param obj
     * @return
     */
    public static Map<String, Object> allFieldObjectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        //Field[] declaredFields = obj.getClass().getDeclaredFields();
        Field[] declaredFields = ArrayUtils.addAll(obj.getClass().getDeclaredFields(), obj.getClass().getSuperclass().getDeclaredFields());
        setObjToMap(obj, map, declaredFields);

        return map;
    }



    private static void setMapToObj(Map<String, Object> map, Object obj, Field[] fields) {
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            try {
                field.set(obj, map.get(field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setObjToMap(Object obj, Map<String, Object> map, Field[] declaredFields) {
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null) {
                    map.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                logger.error("objectToMap报错", e);
            }
        }
    }
}
