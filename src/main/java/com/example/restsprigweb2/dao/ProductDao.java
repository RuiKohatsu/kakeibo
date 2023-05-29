package com.example.restsprigweb2.dao;

import com.example.restsprigweb2.record.ProductRecord;

import java.util.List;

public interface ProductDao {
    List<ProductRecord> findAll();

    ProductRecord findById(int id);

    int insert(String name, int price);

    int update(int id, String name, int price);

    int delete(int id);

}