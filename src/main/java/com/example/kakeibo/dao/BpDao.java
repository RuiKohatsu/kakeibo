package com.example.kakeibo.dao;

import com.example.kakeibo.entity.Bp;
import com.example.kakeibo.entity.BpInsert;
import com.example.kakeibo.entity.BpUpdate;
import com.example.kakeibo.record.BpRecord;
import com.example.kakeibo.record.MonthBp;

import java.util.List;

public interface BpDao {
    List<BpRecord> findAll();

    Bp findById(int rowId);

    int insert(BpInsert bpInsert);

    List<MonthBp> monthBp();

    List<MonthBp> monthBpCategory(Integer[] categories);

    int delete(int id);

    int categoryId(int id);

    int update(BpUpdate bp);

}