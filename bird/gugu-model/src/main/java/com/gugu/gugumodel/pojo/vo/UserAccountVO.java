package com.gugu.gugumodel.pojo.vo;

/**
 * @author ren
 * 用来接收找回密码时用户输入的教工号或学号
 */
public class UserAccountVO {
    Long userAccount;

    public void setUserAccount(Long userAccount) {
        this.userAccount = userAccount;
    }

    public Long getUserAccount() {
        return userAccount;
    }
}
