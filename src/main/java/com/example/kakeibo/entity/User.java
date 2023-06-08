package com.example.kakeibo.entity;

import lombok.Data;

@Data
public class User {
    Integer id;
    String userId;
    String name;
    String loginId;
    String pass;
}
