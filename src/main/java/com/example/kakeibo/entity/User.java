package com.example.kakeibo.entity;

import lombok.Data;

@Data
public class User {
    public Integer id;
    public String userId;
    public String name;
    public String loginId;
    public String pass;

    public User(Integer id, String userId, String name, String loginId, String pass) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.loginId = loginId;
        this.pass = pass;
    }

}
