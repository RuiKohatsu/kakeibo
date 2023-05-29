package com.example.restsprigweb2.service;

import com.example.restsprigweb2.record.ProductRecord;

import java.util.List;

public interface ProductService {
    List<ProductRecord> findAll();

    ProductRecord findById(int id);

    int insert(String name, int price);

    int update(int id, String name, int price);

    int delete(int id);
}
