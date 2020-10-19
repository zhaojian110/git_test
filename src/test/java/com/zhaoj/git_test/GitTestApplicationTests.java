package com.zhaoj.git_test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitTestApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("111");
    }

    @Test
    public void test1() {
        //创建安全管理器工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //创建安全管理器
        SecurityManager securityManager = (SecurityManager) factory.getInstance();

        //将安全管理器交给安全工具类
        SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);

        //根据安全工具类获取主体对象
        Subject subject = SecurityUtils.getSubject();

        //创建令牌对象  令牌=身份信息+凭证信息
        AuthenticationToken token = new UsernamePasswordToken("qifei", "123456");

        //认证
        try {
            subject.login(token);
        } catch (UnknownAccountException u) {
            System.out.println(" 未知的账户异常   用户不存在");
        } catch (IncorrectCredentialsException i) {
            System.out.println(" 不正确的凭证异常   密码错误");
        }

        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证状态: " + authenticated);


        /*
        UnknownAccountException   未知的账户异常     用户不存在
        IncorrectCredentialsException   不正确的凭证异常   密码错误
        *
        * */
    }
}
