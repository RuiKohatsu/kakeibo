package com.example.kakeibo.controller;

import com.example.kakeibo.record.MonthBp;
import com.example.kakeibo.service.BpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestWebController {

    @Autowired
    private BpService bpService;

    @PostMapping("/api/reflect")
    public List<MonthBp> monthCategory(@RequestBody Integer[] categories) {
        var list = bpService.monthBpCategory(categories);
        System.out.println("controller");
        return list;
    }

//    @PutMapping("/api/product/{id}")
//    public int update(@PathVariable("id") int id,@RequestBody String[] namePrice) {
//        bpService.update(id, namePrice[0], Integer.parseInt(namePrice[1]));
//        return 1;
//    }


}