package com.project.common.model;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author wzxie <字段,值> 重新修改了一下，可以完全取代HashMap
 */
public class Row extends HashMap<Object, Object> {

    private static Log logger = LogFactory.getLog(Row.class);

    private static final long serialVersionUID = 1L;

    protected List<Object> ordering = new ArrayList<Object>(); // <字段>

    protected Map<String, String> functionMap = null; // <字段,函数>


    public static Row getInstance() {
        return new Row();
    }

    public static Row getInstance(Map<?, ?> map) {
        return new Row(map);
    }

    /**
     *
     */
    public Row() {
    }

    /**
     * 获取上一级调用参数名，参数必须为包装类
     *
     * @param args
     */
    public static Row getInstance(Object... args) {
        String[] params = getParams(2, args);
        Row instance = Row.getInstance();
        for (int i = 0; i < params.length; i++) {
            instance.put(params[i], args[i]);
        }
        return instance;


    }

    private static String[] getParams(int level, Object[] args) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        StackTraceElement ste = stack[level];

        Class<?> invokeClass = null;
        try {
            invokeClass = Class.forName(ste.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<?>[] methodArgs = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            methodArgs[i] = args[i].getClass();
        }

        Method method = null;
        try {
            method = invokeClass.getMethod(ste.getMethodName(), methodArgs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        return u.getParameterNames(method);
    }

    public Row(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 直接拿一个Map过来当作一个Row使用
     */
    public Row(Map<?, ?> map) {
        super(map);
        for (Object obj : map.keySet()) {
            ordering.add(obj);
        }
    }

    /**
     *
     */
    public boolean getBoolean(Object name) {
        try {
            Object object = get(name);
            if (object != null)
                return (Boolean) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据名字获取map
     *
     * @param name
     * @return
     */
    public Row getRow(Object name) {
        try {
            Object object = get(name);
            if (object != null)
                return (Row) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getDate(Object name) {
        try {
            Object object = get(name);
            if (object != null)
                return (Date) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DateTime getDateTime(Object name) {
        return new DateTime(getDate(name));
    }

    /**
     * 根据字段名返回字符串值
     *
     * @param name
     * @return
     */
    public String getString(Object name) {
        try {
            Object object = get(name);
            if (object != null)
                return String.valueOf(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据字段名返回字符串值,若为null侧返回默认值;
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public String getString(Object name, String defaultValue) {
        try {
            if (get(name) != null)
                return get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 根据字段名返回用指定编码解码字符串值,若为null侧返回默认值;
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public String getString(Object name, String enc, String defaultValue) {
        try {
            if (get(name) != null)
                return URLDecoder.decode(get(name).toString(), enc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * @param name
     * @return
     */
    public int getInt(Object name) {
        return getInt(name, -1);
    }

    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public int getInt(Object name, int defaultValue) {
        Object o = get(name);
        if (o != null) {
            try {
                return Integer.parseInt(o.toString());
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    /**
     * @param which
     * @param defaultValue
     * @return
     */
    public int getInt(int which, int defaultValue) {
        Object key = ordering.get(which);
        return getInt(key, defaultValue);
    }

    /**
     * @param name
     * @return
     */
    public float getFloat(Object name) {
        return Float.valueOf(get(name).toString()).floatValue();
    }

    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public float getFloat(Object name, float defaultValue) {
        Object o = get(name);
        if (o != null)
            try {
                return Float.valueOf(o.toString()).floatValue();
            } catch (Exception e) {
            }
        return defaultValue;
    }

    /**
     * @param which
     * @param defaultValue
     * @return
     */
    public float getFloat(int which, float defaultValue) {
        Object key = ordering.get(which);
        return getFloat(key, defaultValue);
    }

    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public long getLong(Object name, long defaultValue) {
        Object o = get(name);
        if (o != null)
            try {
                return Long.valueOf(o.toString()).longValue();
            } catch (Exception e) {
            }
        return defaultValue;
    }

    public long getLong(Object name) {
        return getLong(name, 0L);
    }

    /**
     * @param which
     * @return
     */
    public Object get(int which) {
        Object key = ordering.get(which);
        return get(key);
    }

    /**
     * @param which
     * @return
     */
    public Object getKey(int which) {
        Object key = ordering.get(which);
        return key;
    }

    public String[] getKeys() {
        Set<Object> keys = this.keySet();
        Iterator<Object> iter = keys.iterator();
        String[] strs = new String[keys.size()];
        int i = 0;
        while (iter.hasNext()) {
            strs[i] = iter.next().toString();
            i++;
        }
        return strs;
    }

    /**
     *
     */
    public void dump() {
        for (Iterator<?> e = keySet().iterator(); e.hasNext(); ) {
            String name = (String) e.next();
            Object value = get(name);
            logger.info(name + "=" + value + ", ");
        }
        logger.info("");
    }

    public String dumpToString() {
        StringBuffer sb = new StringBuffer();
        for (Iterator<?> e = keySet().iterator(); e.hasNext(); ) {
            String name = (String) e.next();
            Object value = get(name);
            sb.append(value).append(",");
        }
        return sb.toString();
    }

    @Override
    public Object get(Object key) {
        return super.get(key);
    }

    /**
     * 
     * @param name
     * @param value
     * @return
     */
    public Row put(Object name, Object value) {
        if (!containsKey(name)) {
            ordering.add(name); // 将键保存起来
        }
        super.put(name, value);
        if (functionMap != null && functionMap.containsKey(name))
            functionMap.remove(name);
        return this;
    }

    /**
     * @param name
     * @param value
     * @return
     */
    public int putInt(Object name, int value) {
        super.put(name, new Integer(value));
        return value;
    }

    /**
     * @param name
     * @param value
     * @return
     */
    public float putFloat(Object name, float value) {
        super.put(name, new Float(value));
        return value;
    }

    /**
     * @param name
     * @param value
     * @return
     */
    public String putFunction(String name, String value) {
        if (functionMap == null)
            functionMap = new HashMap<String, String>();
        if (this != null && this.containsKey(name)) {
            // ordering.remove(name);
            remove(name);
        }
        functionMap.put(name, value);
        return value;
    }

    /**
     * @param name
     * @return
     */
    public String getFunction(String name) {
        return functionMap.get(name);
    }

    public Map<String, String> getFunctionMap() {
        return this.functionMap;
    }

    public Map<String, String> toStringMap() {
        Map<String, String> map = new HashedMap();
        for (Map.Entry<Object, Object> entry : this.entrySet()) {
            map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return map;
    }

    /**
     * @param fmap
     */
    public void setFunctionMap(HashMap<String, String> fmap) {
        if (fmap != null && fmap.size() > 0) {
            if (functionMap == null)
                functionMap = new HashMap<String, String>();
            this.functionMap.putAll(fmap);
        }
    }

    public void putAll(Map<?, ?> otherMap) {
        Set<?> keySet = otherMap.keySet();
        for (Object name : keySet)
            ordering.add(name);
        super.putAll(otherMap);
    }

    public Object remove(Object name) {
        if (ordering.remove(name))
            return super.remove(name);
        return null;
    }

    public void clear() {
        super.clear();
        ordering = null;
    }

    /**
     * 得到行对象中字段的个数
     *
     * @return
     */
    public int length() {
        return size();
    }

}
