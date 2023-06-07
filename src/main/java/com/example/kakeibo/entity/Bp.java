package com.example.kakeibo.entity;

import lombok.Data;

@Data
public class Bp{
    public Integer id;
    public String date;
    public String outIn;
    public String category;
    public Integer amount;
    public String explain;

}