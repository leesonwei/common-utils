/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EmailAuthenticator
 * Author:   Administrator
 * Date:     2018/12/29 17:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anywhere.commonutils.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/29
 * @since 1.0.0
 */
public class EmailAuthenticator extends Authenticator {
    private String username;
    private String password;
    
    public EmailAuthenticator() {
        super();
    }
    
    public EmailAuthenticator(String username, String pwd) {
        super();
        this.username = username;
        this.password = pwd;
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}