package com.hbck.fastworkorder.util;


import android.content.SharedPreferences;

/**
 * @author 丁建强
 * @time 2017-04-06 15:20
 * @类描述：本地存储
 * @变更记录:
 */

public class SPUtil {
    public static String SP_NAME = "FastWorkOrder";
    public static SharedPreferences sp;
    public static final String USER_NAME = "username";//用户名
    public static final String PASSWORD = "password";//密码
    public static final String CITY_ID = "CityId";//城市码
    public static final String EMP_NAME = "emp_name";//姓名
    public static final String REMEMBER_PWD = "remember_pwd";//记住密码
    public static final String TOKEN = "token";//量化token

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    public static String getString(String key) {
        return sp.getString(key, "");
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static int getInt(String key) {
        return sp.getInt(key, -1);
    }


}
