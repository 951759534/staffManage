package org.staffManage.Controller.loginBean;

import lombok.ToString;

/**
 * Created by K550jk on 2017/6/23.
 */
@ToString
public class LoginParam {
    private String name;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    private int admin;
}
