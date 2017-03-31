package com.myproject.bilibili.utils.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chen on 2017/3/29 20:40.
 * 作用:XXXX
 */

@Entity
public class User {

    @Id
    private String username;
    private String password;

    @Transient
    private int tempUsageCount; // not persisted

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Generated(hash = 2090636610)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 586692638)
    public User() {
    }
}
