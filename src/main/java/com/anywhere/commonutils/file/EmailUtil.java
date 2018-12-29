/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EmailUtil
 * Author:   Administrator
 * Date:     2018/12/29 10:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anywhere.commonutils.file;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.PasswordAuthentication;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/29
 * @since 1.0.0
 */
public class EmailUtil {
    private static PropertiesUtil sy_prop = PropertiesUtil.getInstance("email.properties");
    
    /** Sends plain/text email.
     *
     * @param session javax.mail.Session object, might got from jboss jndi.
     * @param recipients {to, cc1, cc2, ...}
     * @param subject
     * @param content
     * @throw IllegalArgumentException
     * @throw MessagingException
     */
    public static void sendMail(Session session, String[] recipients,
                                String subject, String content) throws
            MessagingException, javax.mail.MessagingException {
        
        if (session == null || recipients == null || recipients.length == 0) {
            throw new IllegalArgumentException();
        }
        
        // Message msg = new MimeMessage(session);
        MimeMessage msg=new MimeMessage(session);
        //  msg.setFrom(new InternetAddress(SupplierMail.from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients[0]));
        for (int i = 1; i < recipients.length; i++) {
            msg.setRecipient(Message.RecipientType.CC,
                    new InternetAddress(recipients[i]));
        }
        msg.setSubject(subject,"ISO8859_1");
        msg.setText(content,"ISO8859_1");
        msg.setSentDate(new java.util.Date());
        Transport.send(msg);
    }
    
    /** Sends plain/text email.
     *
     * @param session javax.mail.Session object, might got from jboss jndi.
     * @param recipients {to, cc1, cc2, ...}
     * @param subject
     * @param content
     * @throw IllegalArgumentException
     * @throw MessagingException
     */
    public static void sendMail(Session session, String from, List recipients,
                                String subject, String content) throws
            MessagingException, javax.mail.MessagingException {
        
        if (session == null || recipients == null || recipients.size() == 0) {
            throw new IllegalArgumentException();
        }
        
        // Message msg = new MimeMessage(session);
        MimeMessage msg=new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress((String)recipients.get(0)));
        for (int i = 1; i < recipients.size(); i++) {
            if(recipients.get(i)!=null) {
                msg.setRecipient(Message.RecipientType.CC,
                        new InternetAddress((String)recipients.get(i)));
            }
        }
        msg.setSubject(subject,"GBK");
        msg.setText(content,"GBK");
        msg.setSentDate(new java.util.Date());
        Transport.send(msg);
    }
    
    /** User provides info. to
     *
     * get the Session object to send mail. Uses
     * WebApp.getMailSession() instead, if you use jboss.
     *
     * @param prop
     * @param user
     * @param password
     * @return
     * @see javax.mail.Session
     */
    public static Session getSession(Properties prop, String user,
                                     String password) {
        if (prop == null || user == null || password == null || "".equals(user)) {
            throw new IllegalArgumentException();
        }
        
        //1. copy
        Properties prop0 = new Properties();
        for (Enumeration enum1 = prop.keys(); enum1.hasMoreElements(); ) {
            String key = (String) enum1.nextElement();
            String val = (String) prop.getProperty(key);
            prop0.setProperty(key, val);
        }
        prop0.put("mail.transport.protocol", sy_prop.getProperty("mail.transport.protocol"));
        prop0.put("mail.debug", sy_prop.getProperty("mail.debug")); // debug info.
        prop0.put("mail.smtp.auth", sy_prop.getProperty("mail.smtp.auth")); // 如果server 需要验证
        prop0.put("mail.user", user);
        prop0.put("mail.from", user);
        //2. get
        final String s1 = new String(user), s2 = new String(password);
        Authenticator auth = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(s1, s2);
            }
        };
        Session session = Session.getInstance(prop0, auth);
        return session;
    }
    
    /* ======================================== *
     * Testing Methods
     * ======================================== */
    
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "10.77.78.10");
        //Session session = Session.getDefaultInstance(prop);
        Session session = Session.getInstance(prop,null);
        String from = "admin@admin.com";
        List recipients = new ArrayList();
        recipients.add("admin@admin.com");
        String subject = "EmailUtil testing--测试";
        String content = "13:40:23,437 INFO  [STDOUT] User_Name:admin  Login_Name:admin  IP_Address:127.0.0.1";
        
        sendMail(session, from, recipients, subject, content);
        System.out.println("OK");
        int a=00;
        String aa=new DecimalFormat("00").format(a);
        System.out.println(new DecimalFormat("00").format(a));
    }
}