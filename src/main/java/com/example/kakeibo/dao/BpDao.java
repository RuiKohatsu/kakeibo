package com.example.kakeibo.dao;

import com.example.kakeibo.entity.*;
import com.example.kakeibo.record.BpRecord;
import com.example.kakeibo.record.MonthBp;

import java.util.List;

public interface BpDao {
    User loginCheck(String id, String pass);
    List<BpRecord> findAll();

    Bp findById(int rowId);

    int insert(BpInsert bpInsert);

    List<MonthBp> monthBp();

    List<MonthBp> monthBpCategory(UserCategories userCategories);

    int delete(int id);

    int categoryId(int id);

    int update(BpUpdate bp);

    List<User> userAll();

}