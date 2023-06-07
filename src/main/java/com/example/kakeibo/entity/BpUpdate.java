package com.example.kakeibo.entity;

import lombok.Data;

@Data
public class BpUpdate {
    public Integer id;
    public String date;
    public String outIn;
    public Integer categoryId;
    public Integer amount;
    public String explain;

}