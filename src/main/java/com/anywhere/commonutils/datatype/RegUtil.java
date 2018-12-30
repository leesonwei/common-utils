/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RegUtil
 * Author:   Administrator
 * Date:     2018/12/27 20:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anywhere.commonutils.datatype;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/27
 * @since 1.0.0
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
    
    /**
     * 验证是否是邮箱
     *
     * @param email
     * @return true or false
     */
    private static Pattern emailPattern = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    public static boolean isEmail(String email) {
        if (isNull(email)) {
            return false;
        }
        Matcher m = emailPattern.matcher(email);
        return m.matches();
    }
    
    /**
     * 验证是否是手机号码
     *
     * @param mobileNo
     * @return true or false true or false
     * @Attention
     * 这里使用的是宽松验证,如果需要严格验证需要使用reg_ex
     * reg_ex已经包含新增的16开头的号段
     */
    private static Pattern mobilenoPattern = Pattern.compile("^[1][3-9]\\d{9}$");
    public static boolean isMobileNO(String mobileNo) {
        if (isNull(mobileNo)) {
            return false;
        }
        // String reg_ex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        Matcher m = mobilenoPattern.matcher(mobileNo);
        return m.matches();
    }
    
    /**
     * 验证是否是电话号码(包括手机号和固话)
     *
     * @param str  phone Number!
     * @return true or false true or false
     * @remark 新增16开头的号段
     */
    public static boolean isPhoneNo(String str) {
        if (isNull(str)) {
            return false;
        }
        return isMobileNO(str) || isFixPhone(str);
    }
    
    /**
     * 验证是否是护照号
     *
     * @param passportNO
     * @return true or false
     * @Description:
     */
    private static Pattern passportPattern = Pattern.compile("^1[45][0-9]{7}$|([P|p|S|s]\\d{7}$)|([S|s|G|g|E|e]\\d{8}$)|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8}$)|([H|h|M|m]\\d{8,10})$");
    public static boolean isPassport(String passportNO) {
        if (isNull(passportNO)) {
            return false;
        }
        //String reg = "^1[45][0-9]{7}$|([P|p|S|s]\\d{7}$)|([S|s|G|g|E|e]\\d{8}$)|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8}$)|([H|h|M|m]\\d{8,10})$";
        Matcher m = passportPattern.matcher(passportNO);
        return m.matches();
    }
    
    /**
     * 验证是否是固话号码
     *
     * @param fixPhoneNum 固话号
     * @return true or false
     */
    private static Pattern fixPhonePattern = Pattern.compile("(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" + "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)");
    public static boolean isFixPhone(String fixPhoneNum) {
        if (isNull(fixPhoneNum)) {
            return false;
        }
        //String reg = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" + "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        Matcher m = fixPhonePattern.matcher(fixPhoneNum);
        return m.matches();
    }
    
    /**
     * 校验字符串是否是英文字母，不分大小写
     *
     * @param str
     * @return true or false
     */
    private static Pattern englishPattern = Pattern.compile("^[A-Za-z]+$");
    public static boolean isEnglish(String str) {
        if (isNull(str)) {
            return false;
        }
        Matcher m = englishPattern.matcher(str);
        return m.matches();
    }
    
    /**
     * 验证端口号
     *
     * @param port
     * @return true or false
     */
    private static Pattern portPattern = Pattern.compile("^([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5])$");
    public static boolean isPort(String port) {
        if (isNull(port)) {
            return false;
        }
        //String reg = "^([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5])$";
        Matcher m = portPattern.matcher(port);
        return m.matches();
    }
    
    /**
     * 验证IPv4
     *
     * @param IPv4
     * @return true or false
     */
    private static Pattern ipv4Pattern = Pattern.compile("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
    public static boolean isIPv4(String IPv4) {
        if (isNull(IPv4)) {
            return false;
        }
        //String reg = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
        Matcher m = ipv4Pattern.matcher(IPv4);
        return m.matches();
    }
    
    /**
     * 验证IPv6
     *
     * @param IPv6
     * @return true or false
     */
    public static boolean isIPv6(String IPv6) {
        if (isNull(IPv6)) {
            return false;
        }
        String reg = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";
        return match(reg, IPv6);
    }
    
    /**
     * 验证套接字   ip:port    Socket通常也称作"套接字"，用于描述IP地址和端口
     *
     * @param socket
     * @return true or false
     * @Description: 该函数的功能描述
     */
    public static boolean isSocket(String socket) {
        if (isNull(socket)) {
            return false;
        }
        String reg = "((?:(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\:(?:[0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5]))";
        return match(reg, socket);
    }
    
    /**
     * 验证邮编
     *
     * @param postCode
     * @return true or false
     */
    public static boolean isPostCode(String postCode) {
        if (isNull(postCode)) {
            return false;
        }
        String reg = "[1-9]\\d{5}";
        return match(reg, postCode);
    }
    
    /**
     * 验证是否是用户名
     * 以字母开头，4-20位大小写数字或下划线组成
     *
     * @param userName str
     * @return true or false true or falsetrue 符合规定 false 不符合规定
     */
    private static Pattern usernamePattern = Pattern.compile("^[a-zA-Z]+([a-zA-Z0-9]|[_]){2,20}$");
    public static boolean checkUserName(String userName) {
        if (isNull(userName)) {
            return false;
        }
        Matcher m = usernamePattern.matcher(userName);
        return m.matches();
    }
    
    /**
     * 验证是否是userid  1-19位的正整数   最大 :9223372036854775807  bigint(20)  java Long MAX_VALUE
     *
     * @param userId
     * @return true or false true or false
     */
    private static Pattern useridPattern = Pattern.compile("[1-9][0-9]{0,18}");
    public static boolean isUserId(String userId) {
        if (isNull(userId)) {
            return false;
        }
        Matcher m = useridPattern.matcher(userId);
        return m.matches();
    }
    
    /**
     * 验证密码是否是6-16位
     * 格式为:6-16位，字母区分大小写
     *
     * @param password
     * @return true or false
     */
    public static boolean isPassWord(String password) {
        if (isNull(password)) {
            return false;
        }
        // Pattern p = Pattern.compile("^[0-9a-zA-Z\\.\\_]{6,20}$");
        // Matcher m = p.matcher(password);
        // logger.info(m.matches()+"---");
        int len = password.length();
        boolean bol = true;
        if (len < 6 || len > 16) {
            bol = false;
        }
        return bol;
    }
    
    /**
     * 验证是否是手机验证码
     * 4位或6位数字
     *
     * @param proving
     * @return true or false
     */
    private static Pattern provingPattern = Pattern.compile("\\d{4}|\\d{6}");
    public static boolean isCellPhoneProving(String proving) {
        if (isNull(proving)) {
            return false;
        }
        Matcher m = provingPattern.matcher(proving);
        return m.matches();
    }
    
    /**
     * 验证字符串是否是日期 ,现支持yyyy-MM-dd,yyyyMMdd或yyMMdd三种格式
     *
     * @param dateStr
     * @return true or false
     * @remark 经测试只有yyyy-MM-dd,yyyyMMdd和yyMMdd格式通过,yyyy-MM-dd HH:mm:ss格式不通过
     * @result
     */
    public static boolean isDate(String dateStr) {
        if (isNull(dateStr)) {
            return false;
        }
        String reg = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(dateStr);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * @param str       日期字符串
     * @param formatStr 日期格式化字符串
     * @return true or false
     * @Description: 验证日期字符串合法性
     */
    public static boolean validDateStr(String str, String formatStr) {
        if (isNull(str) || isNull(formatStr)) {
            return false;
        }
        
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }
    
    /**
     * 验证字符串是否是大于等于0的整数
     *
     * @param str
     * @return true or false
     */
    private static Pattern numberPattern = Pattern.compile("^[0-9]+$");
    public static boolean isNumber(String str) {
        if (isNull(str)) {
            return false;
        }
        Matcher m = numberPattern.matcher(str);
        return m.matches();
    }
    
    /**
     * 校验数字,包括小数和负数
     *
     * @param str
     * @return true or false
     */
    private static Pattern digitPattern = Pattern.compile("^-?[0-9]*.?[0-9]*$");
    public static boolean isDigit(String str) {
        if (isNull(str)) {
            return false;
        }
        Matcher m = digitPattern.matcher(str);
        return m.matches();
    }
    
    /**
     * 验证字符串是否是IMEI
     *
     * @param str
     * @return true or false
     * @remark 手机IMEI码由15-17位数字组成,超出15位的为软件版本号,其主体由以下四个部分组成
     * 1,前6位数(TAC，TYPE APPROVAL CODE)是"型号核准号码"，一般代表机型。 　　
     * 2,接着的2位数(FAC-Final Assembly Code)是"最后装配号"，一般代表产地。 　　
     * 3,之后的6位数(SNR)是"串号"，一般代表生产顺序号。 　　
     * 4,最后1位数(SP)为检验码
     */
    public static boolean isIMEI(String str) {
        if (isNull(str) || !isNumber(str)) {
            return false;
        }
        
        if (str.length() == 15 || str.length() == 17) {
            String left = str.substring(0, 14);
            return (String.valueOf(str.charAt(14))).equals(getIMEIGeneCode(left));
        } else {
            if (str.length() == 16) {
                System.out.println("未知的16位的IMEI" + str);
            }
            return false;
        }
        
        /*
         * Pattern p = Pattern.compile("^[0-9]{15}$"); Matcher m = p.matcher(str);
         * return m.matches();
         */
    }
    
    /**
     * 依据传入的14位数字生成IMEI的第15位校验码
     * @param code IMEI的前14位数字
     * @return IMEI的第15位合法校验码
     * @Description: getIMEI15
     */
    public static String getIMEIGeneCode(String code) {
        if (isNull(code)) {
            return "";
        }
        
        if (!isNumber(code) || code.length() != 14) {
            return "";
        }
        int total = 0, sum1 = 0, sum2 = 0;
        int temp = 0;
        char[] chs = code.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int num = chs[i] - '0'; // ascii to num
            // System.out.println(num);
            /* (1)将奇数位数字相加(从1开始计数) */
            if (i % 2 == 0) {
                sum1 = sum1 + num;
            } else {
                /* (2)将偶数位数字分别乘以2,分别计算个位数和十位数之和(从1开始计数) */
                temp = num * 2;
                if (temp < 10) {
                    sum2 = sum2 + temp;
                } else {
                    sum2 = sum2 + temp + 1 - 10;
                }
            }
        }
        total = sum1 + sum2;
        /* 如果得出的数个位是0则校验位为0,否则为10减去个位数 */
        if (total % 10 == 0) {
            return "0";
        } else {
            return (10 - (total % 10)) + "";
        }
    }
    
    /**
     * 验证字符串是否是URL
     *
     * @param str
     * @return true or false
     */
    private static Pattern urlPattern = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
    public static boolean isUrl(String str) {
        if (isNull(str)) {
            return false;
        }
        //String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        Matcher m = urlPattern.matcher(str);
        return m.matches();
    }
    
    /**
     * 是否平板或手机访问
     * @param userAgent
     * @return
     * @version 1.0.0
     * @Attention 注意验证的是访问中的USER-AGENT字段
     * @Demo
     * String userAgent = request.getHeader("USER-AGENT");
     * boolean isphoneVisit =RegUtil.isMobileVisit(userAgent);
     */
    public static boolean isMobileVisit(String userAgent) {
        if (isNull(userAgent)) {
            return false;
        }
        String phoneReg = "\\bNokia|SAMSUNG|MIDP-2|CLDC1.1|SymbianOS|MAUI|UNTRUSTED/1.0" + "|Windows CE|iPhone|iPad|Android|BlackBerry|UCWEB|ucweb|BREW|J2ME|YULONG|YuLong|COOLPAD|TIANYU|TY-" + "|K-Touch|Haier|DOPOD|Lenovo|LENOVO|HUAQIN|AIGO-|CTC/1.0|CTC/2.0|CMCC|DAXIAN"
                + "|MOT-|SonyEricsson|GIONEE|HTC|ZTE|HUAWEI|webOS|GoBrowser|IEMobile|WAP2.0\\b";
        String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser" + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
        
        // 移动设备正则匹配: 手机端 平板
        Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
        Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);
        
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        if (matcherPhone.find() || matcherTable.find()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 验证字符串是否是域名
     *
     * @param str
     * @return true or false
     */
    public static boolean isDomainName(String str) {
        if (isNull(str)) {
            return false;
        }
        // ^在[]里面代表是非数字，所以它代表是非
        String regex = "(^[\\w][^//]*?\\.(?:com\\.cn|net\\.cn|org\\.cn|com|cn|net|org|biz|info|cc|tv)$)";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    
    /**
     * 验证身份证ID
     *
     * @param str
     * @return true or false
     */
    public static boolean isIDNo(String str) {
        if (isNull(str)) {
            return false;
        }
        return isIDCard(str);
        /**
         if (isNull(str)) { return false; }
         String regex = "^(\\d{16}|\\d{18}|\\d{17}(X|x))$";
         return match(regex, str);
         */
    }
    
    /**
     * 验证是否是合法的月数
     *
     * @param str
     * @return true or false
     */
    public static boolean isMonth(String str) {
        if (isNull(str)) {
            return false;
        }
        String regex = "^(0?[[1-9]|1[0-2])$";
        return match(regex, str);
    }
    
    /**
     * 验证是否是合法的天数
     *
     * @param str
     * @return true or false
     */
    public static boolean isDay(String str) {
        if (isNull(str)) {
            return false;
        }
        String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
        return match(regex, str);
    }
    
    /**
     * 验证是否是大写字符串
     *
     * @param str
     * @return true or false
     */
    public static boolean isUpString(String str) {
        if (isNull(str)) {
            return false;
        }
        String regex = "^[A-Z]+$";
        return match(regex, str);
    }
    
    /**
     * 验证是否是小写字符串
     *
     * @param str
     * @return true or false
     */
    public static boolean isLowString(String str) {
        if (isNull(str)) {
            return false;
        }
        String regex = "^[a-z]+$";
        return match(regex, str);
    }
    
    /**
     * 验证是否是汉字字符串
     *
     * @param str
     * @return true or false
     */
    public static boolean isChinese(String str) {
        if (isNull(str)) {
            return false;
        }
        String regex = "^[\u4e00-\u9fa5|\uf900-\ufa2d]+$";
        return match(regex, str);
    }
    
    /**
     * 验证是否是中国名字
     *
     * @param str
     * @return true or false
     */
    public static boolean isChineseName(String str) {
        if (str == null || str.length() == 0 || "".equals(str.trim()) || "NULL".equals(str.trim().toUpperCase())) {
            return false;
        }
        String regex = "^[\u4e00-\u9fa5|\uf900-\ufa2d|·]{2,20}$"; // · ==>Unicode 00B7
        return match(regex, str);
    }
    
    /**
     * 验证是否是符合规则的银行卡号
     *
     * @param str
     * @return true or false
     * @describetion 包括信用卡(14,15,16,19位)和借记卡也就是储蓄卡(16-19位)
     * @remark 注意 工商银行有15位卡号的信用卡和14位信用卡卡号
     */
    public static boolean isBankCardNo(String str) {
        if (isNull(str)) {
            return false;
        }
        String reg = "^([1-9]{1})(\\d{13,18})$";
        return match(reg, str);
    }
    
    /**
     * 验证经度
     *
     * @param value 需要校验的值 如:123.377586
     * @return true or false
     */
    private static Pattern longitudePattern = Pattern.compile("^[\\-\\+]?(0?\\d{1,2}\\.\\d{1,5}|1[0-7]?\\d{1}\\.\\d{1,12}|180\\.0{1,12})$");
    public static boolean checkLongitude(String value) {
        if (isNull(value)) {
            return false;
        }
        Matcher m = longitudePattern.matcher(value);
        return m.matches();
    }
    
    /**
     * 验证纬度
     *
     * @param value 需要校验的值 如: 41.799303
     * @return true or false
     */
    private static Pattern latitudePattern = Pattern.compile("^[\\-\\+]?([0-8]?\\d{1}\\.\\d{1,12}|90\\.0{1,12})$");
    public static boolean checkLatitude(String value) {
        if (isNull(value)) {
            return false;
        }
        Matcher m = latitudePattern.matcher(value);
        return m.matches();
    }
    
    /**
     * 判断字符是否为空或空白字符串,或各种null组成
     */
    private static boolean isNull(String str) {
        return str == null || str.length() == 0 || "".equals(str.trim()) || "NULL".equals(str.trim().toUpperCase()) || "NULLNULL".equals(str.trim().toUpperCase()) || "NULLNULLNULL".equals(str.trim().toUpperCase());
    }
    
    /**
     * 匹配验证辅助方法
     * @param regex
     * @param str
     * @return true or false
     */
    public static boolean match(String regex, String str) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
        // reg =\\< *[img][^\\\\>]*[src] *= *[\\"\\']{0,1}([^\\"\\'\\ >]*) 提取网页中的图片信息
        // reg=(<a\\s*(?!.*\\brel=)[^>]*)(href="https?:\\/\\/)((?!(?:(?:www\\.)?'.implode('|(?:www\\.)?',
        // $follow_list).'))[^"]+)"((?!.*\\brel=)[^>]*)(?:[^>]*)> 提取html页面中的超链接
    }
    
    /**
     * 验证是否是符合官方规则的身份证号 注意:现使用的身份证号有不符合现行的官方身份证号规则的(说白了,就是登记的时候警察叔叔填错了~~ 坑----)
     * @param iDString
     * @return true or false
     */
    public static boolean isIDCard(String iDString) {
        String IDStr = iDString.toUpperCase(); // 这里要注意身份证最后一位 小写x和大写X的问题
        String errorInfo = "";// 记录错误信息
        boolean flag = true;
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 判断字符串是否为空 ================
        if (IDStr == null || IDStr.length() == 0 || IDStr.trim().equals("") || IDStr.trim().toLowerCase().equals("null")) {
            errorInfo = "身份证号码为空或空白字符串或各种大小写的null字符组成";
            flag = false;
            //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            flag = false;
            //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================
        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (RegUtil.isNumber(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            flag = false;
            //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================
        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (RegUtil.isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            flag = false;
            //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
            System.out.println(errorInfo);
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                flag = false;
                //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
                System.out.println(errorInfo);
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            flag = false;
            //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
            System.out.println(errorInfo);
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            flag = false;
            //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
            System.out.println(errorInfo);
            return false;
        }
        // =====================(end)=====================
        // ================ 校验地区码是否有效 =================
        Map<String, String> h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            flag = false;
            //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
            System.out.println(errorInfo);
            return false;
        }
        // ================================================
        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;
        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证最后一位校验不合法，不是合法的身份证号码,合法的身份证号应该为:" + Ai;
                flag = false;
                //log.error("IDStr:{},checkResult:{},errorInfo:{}", IDStr, flag, errorInfo);
                System.out.println(errorInfo);
                return false;
            }
        }
        // =====================(end)=====================
        //log.info("IDStr:{},checkResult:{},Info:{}", IDStr, flag, "恭喜你校验成功!");
        System.out.println("恭喜你校验成功!");
        return flag;
    }
    
    /**
     * 功能:获取身份证号地区编码
     *
     * @return HashMap 对象
     */
    public static Map<String, String> GetAreaCode() {
        Map<String, String> hashtable = new HashMap<String, String>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }
    
    public static void main(String[] args) {
        
        // System.out.println(isPhoneProving("33459"));
        // System.out.println("05711514256601570".length());
        // System.out.println(isIMEI("057115142566015"));
        // System.out.println(isBankCardNo("36091925818434"));
        // System.out.println(isEmail("543884513@qq.com"));
        // System.out.println(isBankCardNo("622908211326127716"));
        
        // System.out.println(isFixPhone("02782665666"));
        // System.out.println(isFixPhone("06686330609"));
        
        // System.out.println(isPort("23355"));
        // System.out.println(isIPv4("10.3.0.222"));
        // System.out.println(isIPv4("000.000.000.000"));
        // System.out.println(isIPv4("0.0.0.0"));
        // System.out.println(isSocket("10.3.0.222:2181"));
        
        // System.out.println(isDomainName("wxapi.9fbank.com"));
        // System.out.println(isDomainName("scgyc.9fbank.com"));
        // System.out.println(isDomainName("www.baidu.com"));
        // System.out.println(isDomainName("https://www.baidu.com"));
        // System.out.println(isDomainName("http://www.baidu.com"));
        // System.out.println(isDomainName("/www.baidu.com"));
        // System.out.println(isDomainName("//www.baidu.com"));
        // System.out.println(isDomainName(":www.baidu.com"));
        // System.out.println(isDomainName("'www.baidu.com"));
        // System.out.println(isDomainName("100.114.89.69:9012"));
        // System.out.println(isDomainName(null));
        // System.out.println(isDomainName(""));
        
        // System.out.println(isDate("2016-10-06"));
        // System.out.println(isDate("2016-10-06 16:24:36"));
        // System.out.println(isDate("2016-10-06 16:24:36:8"));
        // System.out.println(isDate("2016-10-06 16:24:36:89"));
        // System.out.println(isDate("2016-10-06 16:24:36:102"));
        // System.out.println(isDate("20161006"));
        // System.out.println(isDate("20161006162436"));
        // System.out.println(isDate("180712"));
        
        // System.out.println(isDate("20120-10-06"));
        // System.out.println(isDate("2016-100-06"));
        // System.out.println(isDate("2016-10-81"));
        // System.out.println(isDate("100-10-01"));
        
        // String FixedPhone = "0571-8888880-111";
        // System.out.println(isFixPhone("010-89178263"));
        
        // System.out.println(isNumber(" "));
        // System.out.println(isChineseName("粑粑热烈·木木麦提"));
        // System.out.println(RegUtil.isEmail("189666666666@1-89.co-m.cn"));
        // System.out.println(RegUtil.isMobileNO("18866666666"));
        // System.out.println(RegUtil.isUserName("a2_2ss号"));
        // System.out.println(RegUtil.isNumber("0.22222"));
        // System.out.println(RegUtil.isPhoneProving("222_22"));
        // System.out.println(RegUtil.isIMEI("11111111111111"));
        // String cardNo = StringUtil.getNum(16);
        // System.out.println(cardNo);
        // System.out.println(isCardNo(cardNo));
        // System.out.println(isChineseName("王·木木麦提"));
        // System.out.println(isIDCard("41041119921101559X"));
    }
    
}



