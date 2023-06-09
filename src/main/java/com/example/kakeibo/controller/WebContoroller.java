package com.example.kakeibo.controller;

import com.example.kakeibo.entity.Bp;
import com.example.kakeibo.entity.BpInsert;
import com.example.kakeibo.entity.BpUpdate;
import com.example.kakeibo.service.BpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebContoroller {

    @Autowired
    private HttpSession session;

    @Autowired
    private BpService bpService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/menu")
    public String check(Model model){
        if (session.getAttribute("user") == null) {
            return "redirect:/index";  // index.htmlにリダイレクトする場合
        }
        var list = bpService.findAll();
        model.addAttribute("rows", list);
        return "/menu";
    }

    @GetMapping("/insert")
    public String insert(@ModelAttribute("BpInsert") BpInsert bpInsert, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/index";  // index.htmlにリダイレクトする場合
        }
        System.out.println("insert実行");
        var userList = bpService.userAll();
        model.addAttribute("userList", userList);
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("BpInsert") BpInsert bpInsert, BindingResult bindingResult) {
        // バリデーション
        if(bindingResult.hasErrors()) {
            return "/insert";
        }
        System.out.println(bpInsert);

        bpService.insert(bpInsert);
        return "redirect:/menu";
    }


    @GetMapping("/month_bp")
    public String monthBp(Model model){
        if (session.getAttribute("user") == null) {
            return "redirect:/index";  // index.htmlにリダイレクトする場合
        }
        var userList = bpService.userAll();
        for(var user : userList){
            System.out.println(user);
        }
        model.addAttribute("userList", userList);
        var list = bpService.monthBp();
        model.addAttribute("rows", list);
        return "/month_bp";
    }

    @GetMapping("/detail")
    public String detail(@ModelAttribute("bp") Bp bp
            , @RequestParam(name="id") int id
            , Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/index";  // index.htmlにリダイレクトする場合
        }

        var bp1= bpService.findById(id);


        bp.setId(bp1.getId());
        bp.setDate(bp1.getDate());
        bp.setOutIn(bp1.getOutIn());
        bp.setCategory(bp1.getCategory());
        bp.setAmount(bp1.getAmount());
        bp.setExplain(bp1.getExplain());

        System.out.println(bp1.getAmount());

        return "/detail";
    }

    @PostMapping("/delete")
    public String delete( @ModelAttribute("id") int id) {

//        var productId = bp.();
//        System.out.println(productId);

        bpService.delete(id);
        return "redirect:/menu";
    }

    @GetMapping("/updateInput")
    public String updateInput( @ModelAttribute("bp") BpUpdate bp
            , @RequestParam(name="id") int id) {
        if (session.getAttribute("user") == null) {
            return "redirect:/index";  // index.htmlにリダイレクトする場合
        }
        var bp1= bpService.findById(id);
        var categoryId = bpService.categoryId(id);

        bp.setId(id);
        bp.setDate(bp1.getDate());
        bp.setOutIn(bp1.getOutIn());
        bp.setCategoryId(categoryId);
        bp.setAmount(bp1.getAmount());
        bp.setExplain(bp1.getExplain());
        return "updateInput";
    }

    @PostMapping("/update")
    public String update1(@ModelAttribute("bp") BpUpdate bp){

        bpService.update(bp);
        return "redirect:/menu";
    }


}
