/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CloneUtil
 * Author:   Administrator
 * Date:     2018/12/29 16:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anywhere.commonutils.datatype;

import java.io.*;
import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/29
 * @since 1.0.0
 */
public class CloneUtil {
    /**
     * @param obj
     * 			待克隆的对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T cloneObject(T obj) {
        T cloneObj = null;
        try {
            // 写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();
            
            // 分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            // 返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
    
    /**
     * 利用序列化完成集合的深克隆
     * @autor:chenssy
     * @date:2014年8月9日
     *
     * @param collection
     * 					待克隆的集合
     * @return
     * @throws ClassNotFoundException
     * @throws java.io.IOException
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> cloneCollection(Collection<T> collection) throws ClassNotFoundException, IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(collection);
        out.close();
        
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Collection<T> dest = (Collection<T>) in.readObject();
        in.close();
        
        return dest;
    }
}