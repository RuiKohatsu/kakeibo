package com.example.kakeibo.entity;

import lombok.Data;

@Data
public class BpInsert{
    public String date;
    public String outIn;

    public Integer categoryId;
    public Integer amount;
    public String explain;
    public String userId;

}