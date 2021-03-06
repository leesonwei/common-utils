package com.anywhere.commonutils.file;

import com.anywhere.commonutils.datatype.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 使用时对应每个属性文件创建一个类
 */
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;
    private static PropertiesUtil self;
    
    public PropertiesUtil(){}
    
    public PropertiesUtil(String fileName) {
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }
    
    public static PropertiesUtil getInstance(String fileName){
        if (self == null) {
            synchronized (PropertiesUtil.class){
                if (self == null) {
                    self = new PropertiesUtil(fileName);
                }
            }
        }
        return self;
    }

    public String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtil.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtil.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }



}
