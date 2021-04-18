package com.example.gd_lg.encapsulation;

public class LinearUserInfo {
    protected String account;
    protected String password;
    protected String sex;
    protected String remember;
    protected String autoLogin;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(String autoLogin) {
        this.autoLogin = autoLogin;
    }


    @Override
    public String toString() {
        return "用户名：" + account + '\n' +
                "密码：" + password + '\n' +
                "性别：" + sex + '\n' +
                "记住密码：" + remember + '\n' +
                "自动登录：" + autoLogin + '\n';
    }
}
