package com.example.gd_lg.encapsulation;

public class UserInfo {
    protected String account;
    protected String password;
    public void setAccount(String account){
        this.account=account;
    }
    public String getAccount(){
        return this.account;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
}
