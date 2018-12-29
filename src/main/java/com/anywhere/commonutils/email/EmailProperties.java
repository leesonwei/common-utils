/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EmailProperties
 * Author:   Administrator
 * Date:     2018/12/29 17:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anywhere.commonutils.email;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/29
 * @since 1.0.0
 */
public class EmailProperties {
    /**
     * 用户名
     */
    public static String username;
    /**
     * 密码
     */
    public static String password;
    /**
     * smtp
     */
    public static String smtp;
    /**
     * 默认标题
     */
    public static String defaultTitle;
    /**
     * 邮箱名称
     */
    public static String emailName;
    
    static {
        getPath();
    }
    
    /**
     * 获取文件存放路径
     *
     * @return
     */
    private static void getPath() {
        Properties pro = new Properties();
        String path = EmailProperties.class.getClassLoader().getResource("")
                + "email.properties";
        path = path.replace("file:", "");
        File ef = new File(path);
        
        if (ef.exists()) {
            try {
                // 获取jar包外的资源文件
                InputStream is = new FileInputStream(ef);
                pro.load(is);
                // 增加转码处理，当properties中有中文时也可以正常处理
                Set<Object> keyset = pro.keySet();
                Iterator<Object> iter = keyset.iterator();
                while (iter.hasNext()) {
                    String key = (String) iter.next();
                    String newValue = null;
                    try {
                        // 属性配置文件自身的编码
                        String propertiesFileEncode = "utf-8";
                        newValue = new String(pro.getProperty(key).getBytes(
                                "ISO-8859-1"), propertiesFileEncode);
                    } catch (UnsupportedEncodingException e) {
                        newValue = pro.getProperty(key);
                    }
                    pro.setProperty(key, newValue);
                }
            } catch (IOException e) {
                System.err.println("获取email.properties文件信息失败" + e);
            }
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            smtp = pro.getProperty("smtp");
            defaultTitle = pro.getProperty("defaultTitle");
            emailName = pro.getProperty("emailName");
        } else {
            System.out.println("未找到email.properties配置文件");
        }
    }
}