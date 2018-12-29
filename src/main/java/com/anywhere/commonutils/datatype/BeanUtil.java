/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BeanUtil
 * Author:   Administrator
 * Date:     2018/12/29 16:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anywhere.commonutils.datatype;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/29
 * @since 1.0.0
 */
public class BeanUtil {
    /**
     * Bean转换为Map
     *
     * @param object
     * @return String-Object的HashMap
     *
     * @author chenssy
     * @date 2016-09-25
     * @since v1.0.0
     */
    public static Map<String,Object> bean2MapObject(Object object){
        if(object == null){
            return null;
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);
                    
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return map;
    }
    
    /**
     * Map转换为Java Bean
     *
     * @param map
     *              待转换的Map
     * @param object
     *              Java Bean
     * @return java.lang.Object
     *
     * @author chenssy
     * @date 2016-09-25
     * @since v1.0.0
     */
    public static Object map2Bean(Map map,Object object){
        if(map == null || object == null){
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(object, value);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}