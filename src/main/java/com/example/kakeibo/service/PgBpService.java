package com.example.kakeibo.service;

import com.example.kakeibo.NotFoundException;
import com.example.kakeibo.dao.BpDao;
import com.example.kakeibo.entity.Bp;
import com.example.kakeibo.entity.BpInsert;
import com.example.kakeibo.entity.BpUpdate;
import com.example.kakeibo.record.BpRecord;
import com.example.kakeibo.record.MonthBp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PgBpService implements BpService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private BpDao bpDao;


    @Override
    public List<BpRecord> findAll() {
        // 本来は、例外処理が必要
        return bpDao.findAll();
    }

    @Override
    public Bp findById(int rowId) {
        var bp = bpDao.findById(rowId);
        if (bp == null) {
            throw new NotFoundException();
        }else{
            return bp;
        }
    }

    @Override
    public int insert(BpInsert bpInsert) {
        // 本来は、例外処理が必要
        return bpDao.insert(bpInsert);
    }

    @Override
    public List<MonthBp> monthBp() {
        // 本来は、例外処理が必要
        return bpDao.monthBp();
    }

    @Override
    public List<MonthBp> monthBpCategory(Integer[] categories) {
        // 本来は、例外処理が必要
        System.out.println("service");
        return bpDao.monthBpCategory(categories);
    }

    @Override
    public int delete(int id) {
        // 本来は、例外処理が必要
        return bpDao.delete(id);
    }

    @Override
    public int categoryId(int id) {
        // 本来は、例外処理が必要
        return bpDao.categoryId(id);
    }

    @Override
    public int update(BpUpdate bp) {
        // 本来は、例外処理が必要
        return bpDao.update(bp);
    }
}
