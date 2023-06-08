package com.example.kakeibo.controller;

import com.example.kakeibo.entity.User;
import com.example.kakeibo.entity.UserCategories;
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

    @PostMapping("/api/login")
    public int login(@RequestBody String[] inputData) {
        var id = inputData[0];
        var pass = inputData[1];
        if(id.equals("") && !pass.equals("")){
            return 0;
        }else if(!id.equals("") && pass.equals("")){
            return 1;
        }else if(id.equals("") && pass.equals("")){
            return 2;
        }else{
            var user = bpService.loginCheck(id, pass);
            if(user == null) {
                return 3;
            }else{
                var sessionUser = new SessionUser(1, user.getName(),user.getRole());
                System.out.println(user.name);
                session.setAttribute("user", sessionUser);
                return 4;
            }
        }
    }

    @PostMapping("/api/reflect")
    public List<MonthBp> monthCategory(@RequestBody UserCategories userCategories) {
        System.out.println("Restコントローラー/カテゴリ"+userCategories.getCategories());
        var list = bpService.monthBpCategory(userCategories);
        System.out.println("controller");
        return list;
    }

    @GetMapping("/api/selectBox")
    public List<User> selectBox() {
        var list = bpService.userAll();
        for(var user : list){
            System.out.println(user);
        }
        return list;
    }




}