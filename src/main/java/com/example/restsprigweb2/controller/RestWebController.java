package com.example.restsprigweb2.controller;

import com.example.restsprigweb2.entity.Product;
import com.example.restsprigweb2.record.ProductRecord;
import com.example.restsprigweb2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestWebController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    //@ResponseBodyを使用するとinput文字列が返されるだけとなる
    public List<ProductRecord> productList() {
        //var list = productService.findAll();
        var list = productService.findAll();
        return list;
    }

    @PostMapping("/api/product")
    public int insert(@RequestBody Product requestData) {
        var name = requestData.getName();
        var price = Integer.parseInt(requestData.getPrice());
        System.out.println("AAAAAAAAAAA" + price);
        productService.insert(name, price);
        return 1;
    }

    @DeleteMapping("/api/product/{id}")
    public int delete(@PathVariable("id") int id) {
        productService.delete(id);
        return 1;
    }

    @PutMapping("/api/product/{id}")
    public int update(@PathVariable("id") int id,@RequestBody String[] namePrice) {
        productService.update(id,namePrice[0],Integer.parseInt(namePrice[1]));
        return 1;
    }




}